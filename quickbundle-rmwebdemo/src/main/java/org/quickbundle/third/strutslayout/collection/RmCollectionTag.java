package org.quickbundle.third.strutslayout.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.struts.taglib.html.Constants;
import org.apache.struts.util.MessageResources;
import org.quickbundle.third.strutslayout.util.RmBasicCollection;
import org.quickbundle.tools.helper.RmJspHelper;
import org.quickbundle.util.RmSequenceMap;

import fr.improve.struts.taglib.layout.collection.CollectionTag;
import fr.improve.struts.taglib.layout.collection.CollectionsIterator;
import fr.improve.struts.taglib.layout.collection.header.CollectionItemEvent;
import fr.improve.struts.taglib.layout.collection.header.MultiLevelHeader;
import fr.improve.struts.taglib.layout.el.Expression;
import fr.improve.struts.taglib.layout.event.StartLayoutEvent;
import fr.improve.struts.taglib.layout.field.AbstractModeFieldTag;
import fr.improve.struts.taglib.layout.sort.SortUtil;
import fr.improve.struts.taglib.layout.util.IFilterableHeaderRenderer;
import fr.improve.struts.taglib.layout.util.IMultiLevelHeaderRenderer;
import fr.improve.struts.taglib.layout.util.ISelectionCellRenderer;
import fr.improve.struts.taglib.layout.util.LayoutUtils;
import fr.improve.struts.taglib.layout.util.TagUtils;

public class RmCollectionTag extends CollectionTag {
	
	/**
	 * 表头是否锁定，不随着垂直滚轴而滑动或隐藏
	 */
	protected boolean headLocked = false;
	public String getHeadLocked() {
		return String.valueOf(headLocked);
	}
	public void setHeadLocked(String headLocked_) {
		this.headLocked = "true".equalsIgnoreCase(headLocked_);
	}
	
	/**
	 * 未锁定的列的宽度
	 */
	protected String lineWrap;

	public String getLineWrap() {
		return lineWrap;
	}

	public void setLineWrap(String lineWrap) {
		this.lineWrap = lineWrap;
	}

	public static ThreadLocal<RmCollectionItemTag> tlItemTag = new ThreadLocal<RmCollectionItemTag>();
	/**
	 * 记录锁定、未锁定列的header - item
	 */
	Map<MultiLevelHeader, RmCollectionItemTag> mHeadItem = new RmSequenceMap<MultiLevelHeader, RmCollectionItemTag>();
	
	protected static MessageResources messages =
		MessageResources.getMessageResources(Constants.Package + ".LocalStrings");
	
	/* (non-Javadoc)
	 * @see fr.improve.struts.taglib.layout.collection.BaseCollectionTag#getSortType()
	 */
	public int getSortType() {
		return sortType;
	}
	
	protected void initPanel(PageContext in_pageContext) {	
		defaultPanel = new RmBasicCollection();
		panel = defaultPanel;
		super.setEmptyKey("<div style=\"align:left;padding-left:30px\">没有相关记录！</div>");
	}
	
	public RmBasicCollection getPanel() {
		return (RmBasicCollection)panel;
	}
	
	protected String computeSortUrl(String sortProperty) throws JspException {
		String lc_sortUrl = null;
		if (sortProperty!=null) {
			StringBuffer lc_tempBuffer = new StringBuffer();	
			switch (sortType) {
				case SORT_LAYOUT:
					String url = SortUtil.getURLForCollection(pageContext, sortProperty);
					lc_tempBuffer.append(url);
					break;
				case SORT_CUSTOM:
					// use user custom sort action.								
					if (!sortAction.toLowerCase().startsWith("javascript:")) {
						// No js code : go to the server to sort.
                        if("0".equals(sortAction))
                            lc_tempBuffer.append(RmJspHelper.getOrderURL(sortProperty, (HttpServletRequest)pageContext.getRequest(), null, sortParam));
                        else
                            lc_tempBuffer.append(RmJspHelper.getOrderURL(sortProperty, (HttpServletRequest)pageContext.getRequest(), sortAction, sortParam));

					} else {
						// Js code : need to put the right parameters at their place.					
				        String javascriptSortParamName = "sortParam";
						if (sortParam != null && sortParam.trim().length() > 0) {
							javascriptSortParamName = sortParam;
						}
						pageContext.setAttribute(javascriptSortParamName, sortProperty);					
						lc_tempBuffer.append(Expression.evaluate(sortAction, pageContext));
						pageContext.removeAttribute(javascriptSortParamName);
					}
					break;				
				case SORT_JAVASCRIPT:
					// sorting on the client browser with javascript.
					lc_tempBuffer.append("javascript:arraySort(");
					lc_tempBuffer.append(sortParam);
					lc_tempBuffer.append(",");
					lc_tempBuffer.append(column);
					lc_tempBuffer.append(",");
					lc_tempBuffer.append(size);
					lc_tempBuffer.append(",");
					lc_tempBuffer.append(nbOfColumns);
					lc_tempBuffer.append(")");
					break;
			}
			lc_sortUrl = lc_tempBuffer.toString();
		}
		return lc_sortUrl;
	}
	
	public int doStartLayoutTag() throws JspException {		
		
		if (fieldDisplayMode == AbstractModeFieldTag.MODE_NODISPLAY)
			return SKIP_BODY;				

		String beanNullMsg = messages.getMessage("getter.bean", name);
		// get the first collection and iterator
		Object lc_bean = null;
		try {
			//改成空Collection能正常显示页面为"无相关记录"
			lc_bean = LayoutUtils.getBeanFromPageContext(pageContext, name, property);
		} catch (JspException e) {
			if(e.toString().indexOf(beanNullMsg) > -1) {
				lc_bean = new ArrayList();
			} else {
				throw e;
			}
		}
		Collection lc_collection = LayoutUtils.getCollection(lc_bean);
		Object lc_mainBean = null;
		try {
			//改成空Collection能正常显示页面为"无相关记录"
			lc_mainBean = LayoutUtils.getBeanFromPageContext(pageContext, name, null);
		} catch (JspException e) {
			if(e.toString().indexOf(beanNullMsg) > -1) {
				lc_mainBean = new ArrayList();
			} else {
				throw e;
			}
		}
		iterator = new CollectionsIterator(lc_mainBean, property, id, indexId); //lc_collection.iterator();

		size = lc_collection.size();
		
		// Inform our parent layout tag we are starting.
		// We must do this after having initialized the size attribute so that
		// a potential parent pager tag can get the collection size.  
		new StartLayoutEvent(this, new StringBuffer("<td colspan=\"").append(LayoutUtils.getSkin(pageContext.getSession()).getFieldInterface().getColumnNumber()).append("\" valign=\"top\">")).send();
		
		// get the second collection and iterator
		if (name2 != null)
			iterator2 = LayoutUtils.getIterator(pageContext, name2, property2);

		StringBuffer buffer = new StringBuffer();

		// Init the panel
		if (panel==null) {
			initPanel(pageContext);
		}
		panel.init(pageContext, styleClass, this);

		// If the collection is empty, display the specified error message.
//	if (!iterator.hasNext(pageContext) && emptyKey!=null) {
//		renderBlankCollection(buffer);
//		TagUtils.write(pageContext, buffer.toString());		
//		return SKIP_BODY;
//	} else {
		renderStart(buffer);
		TagUtils.write(pageContext, buffer.toString());
		return EVAL_BODY_TAG;
//	}
	}
	
	public Object addCollectionTitle(CollectionItemEvent in_event) {
		MultiLevelHeader lc_header = (MultiLevelHeader) in_event.getValue();
		if (multiLevelHeaders==null) {
			multiLevelHeaders = new ArrayList();
		}
		multiLevelHeaders.add(lc_header);
		headersLevel = Math.max(headersLevel, lc_header.getLevel());
		lc_header.setMaxLevel(lc_header.getLevel() == headersLevel);
		//头信息加入map
		mHeadItem.put(lc_header, (RmCollectionItemTag)in_event.getSource());
		return null;
	}
	
	/**
	 * Print a column title to the buffer. 
	 * (multi level header)
	 */
	protected void renderMultiLevelHeaders(StringBuffer in_buffer, List in_multiLevelHeaders, int in_level) throws JspException {
		if (in_multiLevelHeaders!=null) {
			columnInLine = in_multiLevelHeaders.size();
			List lc_nestedLevels = null;
			Iterator lc_it = in_multiLevelHeaders.iterator();
			IMultiLevelHeaderRenderer lc_panel = (IMultiLevelHeaderRenderer) panel;			
			column = 0;			
			
			// Did we start a header row ?
			boolean lc_started = false;
			
			while (lc_it.hasNext()) {				
				MultiLevelHeader lc_header = (MultiLevelHeader) lc_it.next();
				List lc_headerChildren = lc_header.getChildHeaders();
				int lc_rowSpan = lc_headerChildren!=null ? 1 : in_level+1 - lc_header.getLevel();
				
				Object[] lc_args = new Object[2];
				lc_args[0] = Expression.evaluate(lc_header.getArg0(), pageContext);
				lc_args[1] = Expression.evaluate(lc_header.getArg1(), pageContext);
				String lc_title = LayoutUtils.getLabel(pageContext, getBundle(), lc_header.getTitle(), lc_args, false);
				String lc_sortUrl = computeSortUrl(lc_header.getSortProperty());
				lc_header.setSortUrl(lc_sortUrl);
				String lc_styleClass = lc_header.getStyleClass()==null ? getStyleClass() : lc_header.getStyleClass();
				String lc_tooltip = LayoutUtils.getLabel(pageContext, getBundle(), lc_header.getTooltip(), null, false);
//				currentHeader = lc_header;
				
				if (lc_title!=null) {
					// Title is not null, render it.
					
					if (!lc_started) {
						// First time we render a title, start a title row.
						lc_panel.startMultiLevelHeaderRow(in_buffer);

						// render the header selection cell
						if (lc_panel instanceof ISelectionCellRenderer) {
							if (lc_header.isMaxLevel()) { // TODO A revoir ? (Gil)
								if (((selectName != null) || (selectProperty != null)) && !selectHidden) {
//									column++;
//									columnInLine++;
									Map defaultAttributes = getDefaultAttributesForSelectionCell();
									((ISelectionCellRenderer)lc_panel).renderSelectionCell(in_buffer, defaultAttributes, -in_level);
								}
							}
						}

						lc_started = true;
					}
					
					// Render the title.
					if (panel instanceof IFilterableHeaderRenderer) {
						StringBuffer titleBuffer = new StringBuffer();
						((IFilterableHeaderRenderer)panel).renderInnerFilterableHeader(titleBuffer, lc_header);
						lc_title = titleBuffer.toString();
					}
					((RmBasicCollection)lc_panel).renderMultiLevelHeader(in_buffer, lc_title, lc_sortUrl, lc_styleClass, lc_header.getColSpan(), lc_rowSpan, lc_header.getWidth(), mHeadItem.get(lc_header).locked);
				}
//				currentHeader = null;
				if (lc_headerChildren!=null) {
					if (lc_nestedLevels==null) {
						lc_nestedLevels = new ArrayList();
					}
					lc_nestedLevels.addAll(lc_headerChildren);
				}
				column++;
			}			
			if(lc_started) {
				lc_panel.endMultiLevelHeaderRow(in_buffer);
			}
			renderMultiLevelHeaders(in_buffer, lc_nestedLevels, in_level-1);
		}
	}
	
	private Map getDefaultAttributesForSelectionCell() throws JspException {
		Map result = new HashMap();

		// "type" attribute
		if ("checkbox".equalsIgnoreCase(selectType)) {
			result.put("type", "checkbox");	
		} else {
			result.put("type", "radio");	
		}

		// "name" attribute
		StringBuffer buffer = new StringBuffer();
		if (selectName!=null) {
			buffer.append(selectName);
		} else {
			buffer.append(selectProperty);	
		}	
		if ("checkbox".equalsIgnoreCase(selectType)) {
			if (selectId!=null) {
				buffer.append("(");
				buffer.append(LayoutUtils.getProperty(bean, selectId));
				buffer.append(")");
			} else {
				buffer.append("[");
				buffer.append(index-1);
				buffer.append("]");
				if (selectIndex!=null) {
					buffer.append(".");
					buffer.append(selectIndex);
				}
			}							
		}
		result.put("name", buffer.toString());

		// "onClick" attribute
		if (onClick!=null) {
			result.put("onclick", onClick);	
		}

		// "value" attribute"		
//		buffer.setLength(0);
//		buffer.append("\" value=\"");
		Object lc_value = bean==null ? "" : LayoutUtils.getProperty(bean, selectProperty);
//		buffer.append(lc_value);
//		buffer.append("\"");
		result.put("value", lc_value);
		
		// "checked" attribute.
		if (bean!=null && isCurrentBeanSelected()) {
			result.put("checked", "checked");
		}

		return result;
	}
}