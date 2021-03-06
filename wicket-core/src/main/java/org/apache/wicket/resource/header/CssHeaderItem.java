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
package org.apache.wicket.resource.header;

import org.apache.wicket.request.Response;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.string.Strings;

/**
 * Base class for all {@link HeaderItem}s that represent stylesheets. This class mainly contains
 * factory methods.
 * 
 * @author papegaaij
 */
public abstract class CssHeaderItem extends HeaderItem
{
	/**
	 * Creates a {@link CssReferenceHeaderItem} for the given reference.
	 * 
	 * @param reference
	 *            a reference to a CSS resource
	 * @return A newly created {@link CssReferenceHeaderItem} for the given reference.
	 */
	public static CssReferenceHeaderItem forReference(ResourceReference reference)
	{
		return new CssReferenceHeaderItem(reference, null, null, null);
	}

	/**
	 * Creates a {@link CssReferenceHeaderItem} for the given reference.
	 * 
	 * @param reference
	 *            a reference to a CSS resource
	 * @param media
	 *            the media type for this CSS ("print", "screen", etc.)
	 * @return A newly created {@link CssReferenceHeaderItem} for the given reference.
	 */
	public static CssReferenceHeaderItem forReference(ResourceReference reference, String media)
	{
		return new CssReferenceHeaderItem(reference, null, media, null);
	}

	/**
	 * Creates a {@link CssReferenceHeaderItem} for the given reference.
	 * 
	 * @param reference
	 *            a reference to a CSS resource
	 * @param pageParameters
	 *            the parameters for this CSS resource reference
	 * @param media
	 *            the media type for this CSS ("print", "screen", etc.)
	 * @return A newly created {@link CssReferenceHeaderItem} for the given reference.
	 */
	public static CssReferenceHeaderItem forReference(ResourceReference reference,
		PageParameters pageParameters, String media)
	{
		return new CssReferenceHeaderItem(reference, pageParameters, media, null);
	}

	/**
	 * Creates a {@link CssReferenceHeaderItem} for the given reference.
	 * 
	 * @param reference
	 *            a reference to a CSS resource
	 * @param pageParameters
	 *            the parameters for this CSS resource reference
	 * @param media
	 *            the media type for this CSS ("print", "screen", etc.)
	 * @param condition
	 *            the condition to use for Internet Explorer conditional comments. E.g. "IE 7".
	 * @return A newly created {@link CssReferenceHeaderItem} for the given reference.
	 */
	public static CssReferenceHeaderItem forReference(ResourceReference reference,
		PageParameters pageParameters, String media, String condition)
	{
		return new CssReferenceHeaderItem(reference, pageParameters, media, condition);
	}

	/**
	 * Creates a {@link CssContentHeaderItem} for the given content.
	 * 
	 * @param css
	 *            css content to be rendered.
	 * @param id
	 *            unique id for the &lt;style&gt; element. This can be <code>null</code>, however in
	 *            that case the ajax header contribution can't detect duplicate CSS fragments.
	 * @return A newly created {@link CssContentHeaderItem} for the given content.
	 */
	public static CssContentHeaderItem forCSS(CharSequence css, String id)
	{
		return new CssContentHeaderItem(css, id);
	}

	/**
	 * Creates a {@link CssUrlReferenceHeaderItem} for the given url.
	 * 
	 * @param url
	 *            context-relative url of the CSS resource
	 * @return A newly created {@link CssUrlReferenceHeaderItem} for the given url.
	 */
	public static CssUrlReferenceHeaderItem forUrl(String url)
	{
		return new CssUrlReferenceHeaderItem(url, null, null);
	}

	/**
	 * Creates a {@link CssUrlReferenceHeaderItem} for the given url.
	 * 
	 * @param url
	 *            context-relative url of the CSS resource
	 * @param media
	 *            the media type for this CSS ("print", "screen", etc.)
	 * @return A newly created {@link CssUrlReferenceHeaderItem} for the given url.
	 */
	public static CssUrlReferenceHeaderItem forUrl(String url, String media)
	{
		return new CssUrlReferenceHeaderItem(url, media, null);
	}

	/**
	 * Creates a {@link CssUrlReferenceHeaderItem} for the given url.
	 * 
	 * @param url
	 *            context-relative url of the CSS resource
	 * @param media
	 *            the media type for this CSS ("print", "screen", etc.)
	 * @param condition
	 *            the condition to use for Internet Explorer conditional comments. E.g. "IE 7".
	 * @return A newly created {@link CssUrlReferenceHeaderItem} for the given url.
	 */
	public static CssUrlReferenceHeaderItem forUrl(String url, String media, String condition)
	{
		return new CssUrlReferenceHeaderItem(url, media, condition);
	}

	protected static void internalRenderCSSReference(Response response, String url, String media,
		String condition)
	{
		if (Strings.isEmpty(url))
		{
			throw new IllegalArgumentException("url cannot be empty or null");
		}
		String urlWoSessionId = Strings.stripJSessionId(url);
		if (Strings.isEmpty(condition) == false)
		{
			response.write("<!--[if ");
			response.write(condition);
			response.write("]>");
		}
		response.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
		response.write(urlWoSessionId);
		response.write("\"");
		if (media != null)
		{
			response.write(" media=\"");
			response.write(media);
			response.write("\"");
		}
		response.write(" />");
		if (Strings.isEmpty(condition) == false)
		{
			response.write("<![endif]-->");
		}
		response.write("\n");
	}
}
