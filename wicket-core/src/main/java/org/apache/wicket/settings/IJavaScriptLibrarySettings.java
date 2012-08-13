/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.wicket.settings;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.resource.JQueryResourceReference;


/**
 * Interface for settings related to the JavaScript libraries that come with and are used by Wicket.
 * <p>
 * With these settings the user application can replace the JavaScript libraries used for Wicket's
 * event and Ajax functionality. By default Wicket uses {@linkplain JQueryResourceReference JQuery}
 * as a backing library but via this interface the application can replace the implementations of
 * wicket-event.js, wicket-ajax.js and wicket-ajax-debug.js to use implementations on other
 * libraries, such as YUI or DOJO. The header item implementations need to specify the
 * {@linkplain HeaderItem#getDependencies() dependency} on the backing library, if needed.
 * 
 * @since 6.0
 */
public interface IJavaScriptLibrarySettings
{

	/**
	 * @return the header contribution for the JQuery JavaScript library used as backing library for
	 *         wicket-event and wicket-ajax
	 */
	HeaderItem getJQueryHeaderItem();

	/**
	 * @param contribution 
	 *            a header contribution for the JQuery JavaScript library used as backing library for
	 *            wicket-event and wicket-ajax
	 */
	void setJQueryHeaderItem(HeaderItem contribution);

	/**
	 * @return the header item for the implementation of wicket-event.js
	 */
	HeaderItem getWicketEventHeaderItem();

	/**
	 * @param contribution
	 *            a header contribution for the implementation of wicket-event.js
	 */
	void setWicketEventHeaderItem(HeaderItem contribution);

	/**
	 * @return the header contribution for the implementation of wicket-ajax.js
	 */
	HeaderItem getWicketAjaxHeaderItem();

	/**
	 * @param contribution
	 *            a header contribution for the implementation of wicket-ajax.js
	 */
	void setWicketAjaxHeaderItem(HeaderItem contribution);

	/**
	 * The Wicket Ajax Debug Window.
	 * 
	 * @return the header contribution for the implementation of wicket-ajax-debug.js
	 */
	HeaderItem getWicketAjaxDebugHeaderItem();

	/**
	 * @param contribution
	 *            a header contribution for the implementation of wicket-ajax-debug.js
	 */
	void setWicketAjaxDebugHeaderItem(HeaderItem contribution);
}
