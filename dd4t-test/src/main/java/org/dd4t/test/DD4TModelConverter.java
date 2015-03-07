package org.dd4t.test;
/**
 * Created by rai on 03/06/14.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.dd4t.contentmodel.ComponentPresentation;
import org.dd4t.contentmodel.impl.*;
import org.dd4t.core.exceptions.SerializationException;
import org.dd4t.databind.DataBindFactory;
import org.dd4t.databind.serializers.json.ComponentPresentationDeserializer;
import org.dd4t.databind.serializers.xml.XmlBaseFieldsDeserializer;
import org.dd4t.databind.serializers.xml.XmlValuesDeserializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.xml.stream.XMLStreamException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class DD4TModelConverter {

	static String test4 = "{    \"RevisionDate\": \"2015-01-11T14:09:22\",    \"Filename\": \"story\",    \"LastPublishedDate\": \"0001-01-01T00:00:00\",    \"PageTemplate\": {        \"FileExtension\": \"html\",        \"RevisionDate\": \"2015-01-11T12:10:43\",        \"MetadataFields\": {            \"viewName\": {                \"Name\": \"viewName\",                \"Values\": [                    \"contentPage\"                ],                \"FieldType\": 0            }        },        \"Folder\": {            \"PublicationId\": \"tcm:0-30-1\",            \"Id\": \"tcm:30-174-2\",            \"Title\": \"DD4T Page Views\"        },        \"Publication\": {            \"Id\": \"tcm:0-30-1\",            \"Title\": \"600 Urdu Website\"        },        \"OwningPublication\": {            \"Id\": \"tcm:0-12-1\",            \"Title\": \"200 Master Templates\"        },        \"Id\": \"tcm:30-862-128\",        \"Title\": \"Content Page\"    },    \"MetadataFields\": {},    \"ComponentPresentations\": [        {            \"Component\": {                \"LastPublishedDate\": \"0001-01-01T00:00:00\",                \"RevisionDate\": \"2015-01-14T13:51:25\",                \"Schema\": {                    \"Folder\": {                        \"PublicationId\": \"tcm:0-30-1\",                        \"Id\": \"tcm:30-18-2\",                        \"Title\": \"Editorial Schemas\"                    },                    \"RootElementName\": \"Story\",                    \"Publication\": {                        \"Id\": \"tcm:0-30-1\",                        \"Title\": \"600 Urdu Website\"                    },                    \"Id\": \"tcm:30-784-8\",                    \"Title\": \"Story\"                },                \"Fields\": {                    \"heading\": {                        \"Name\": \"heading\",                        \"Values\": [                            \"Make my future mine\"                        ],                        \"FieldType\": 0,                        \"XPath\": \"tcm:Content/custom:Story/custom:heading\"                    },                    \"paragraph\": {                        \"Name\": \"paragraph\",                        \"EmbeddedValues\": [                            {                                \"paragraph\": {                                    \"Name\": \"paragraph\",                                    \"Values\": [                                        \"Our brand dolor sit amet, consectetur adipiscing elit. Pellentesque eget sem sit amet mi tempor venenatis. Proin nec fringilla risus. Donec dolor enim, fermentum nec.\"                                    ],                                    \"FieldType\": 2,                                    \"XPath\": \"tcm:Content/custom:Story/custom:paragraph[1]/custom:paragraph\"                                }                            }                        ],                        \"EmbeddedSchema\": {                            \"Folder\": {                                \"PublicationId\": \"tcm:0-30-1\",                                \"Id\": \"tcm:30-16-2\",                                \"Title\": \"Embeddable Schemas\"                            },                            \"RootElementName\": \"Paragraph\",                            \"Publication\": {                                \"Id\": \"tcm:0-30-1\",                                \"Title\": \"600 Urdu Website\"                            },                            \"Id\": \"tcm:30-783-8\",                            \"Title\": \"Paragraph\"                        },                        \"FieldType\": 4,                        \"XPath\": \"tcm:Content/custom:Story/custom:paragraph\"                    },                    \"multimedia\": {                        \"Name\": \"multimedia\",                        \"Values\": [                            \"tcm:30-1060\"                        ],                        \"LinkedComponentValues\": [                            {                                \"LastPublishedDate\": \"0001-01-01T00:00:00\",                                \"RevisionDate\": \"2015-01-14T13:49:49\",                                \"Schema\": {                                    \"Folder\": {                                        \"PublicationId\": \"tcm:0-30-1\",                                        \"Id\": \"tcm:30-18-2\",                                        \"Title\": \"Editorial Schemas\"                                    },                                    \"RootElementName\": \"ImageWrapper\",                                    \"Publication\": {                                        \"Id\": \"tcm:0-30-1\",                                        \"Title\": \"600 Urdu Website\"                                    },                                    \"Id\": \"tcm:30-1025-8\",                                    \"Title\": \"ImageWrapper\"                                },                                \"Fields\": {                                    \"multimedia\": {                                        \"Name\": \"multimedia\",                                        \"Values\": [                                            \"tcm:30-176\"                                        ],                                        \"LinkedComponentValues\": [                                            {                                                \"LastPublishedDate\": \"0001-01-01T00:00:00\",                                                \"RevisionDate\": \"2014-11-27T16:49:33\",                                                \"Schema\": {                                                    \"Folder\": {                                                        \"PublicationId\": \"tcm:0-30-1\",                                                        \"Id\": \"tcm:30-17-2\",                                                        \"Title\": \"Multimedia Schemas\"                                                    },                                                    \"RootElementName\": \"undefined\",                                                    \"Publication\": {                                                        \"Id\": \"tcm:0-30-1\",                                                        \"Title\": \"600 Urdu Website\"                                                    },                                                    \"Id\": \"tcm:30-154-8\",                                                    \"Title\": \"Image\"                                                },                                                \"Fields\": {},                                                \"MetadataFields\": {                                                    \"AltText\": {                                                        \"Name\": \"AltText\",                                                        \"Values\": [                                                            \"mouse-icon\"                                                        ],                                                        \"FieldType\": 0,                                                        \"XPath\": \"tcm:Metadata/custom:Metadata/custom:AltText\"                                                    }                                                },                                                \"ComponentType\": 0,                                                \"Multimedia\": {                                                    \"Url\": \"/Preview/Images/mouse-icon_tcm30-176.png\",                                                    \"MimeType\": \"image/png\",                                                    \"FileName\": \"mouse-icon.png\",                                                    \"FileExtension\": \"png\",                                                    \"Size\": 33681,                                                    \"Width\": 0,                                                    \"Height\": 0                                                },                                                \"Folder\": {                                                    \"PublicationId\": \"tcm:0-30-1\",                                                    \"Id\": \"tcm:30-24-2\",                                                    \"Title\": \"Multimedia\"                                                },                                                \"Categories\": [],                                                \"Version\": 3,                                                \"Publication\": {                                                    \"Id\": \"tcm:0-30-1\",                                                    \"Title\": \"600 Urdu Website\"                                                },                                                \"OwningPublication\": {                                                    \"Id\": \"tcm:0-5-1\",                                                    \"Title\": \"200 Master Content (ENG)\"                                                },                                                \"Id\": \"tcm:30-176\",                                                \"Title\": \"Mouse-Icon.png\"                                            }                                        ],                                        \"FieldType\": 5,                                        \"XPath\": \"tcm:Content/custom:ImageWrapper/custom:multimedia\"                                    },                                    \"altText\": {                                        \"Name\": \"altText\",                                        \"Values\": [                                            \"Description of the image\"                                        ],                                        \"FieldType\": 0,                                        \"XPath\": \"tcm:Content/custom:ImageWrapper/custom:altText\"                                    }                                },                                \"MetadataFields\": {},                                \"ComponentType\": 1,                                \"Folder\": {                                    \"PublicationId\": \"tcm:0-30-1\",                                    \"Id\": \"tcm:30-205-2\",                                    \"Title\": \"Stories\"                                },                                \"Categories\": [],                                \"Version\": 1,                                \"Publication\": {                                    \"Id\": \"tcm:0-30-1\",                                    \"Title\": \"600 Urdu Website\"                                },                                \"OwningPublication\": {                                    \"Id\": \"tcm:0-5-1\",                                    \"Title\": \"200 Master Content (ENG)\"                                },                                \"Id\": \"tcm:30-1060\",                                \"Title\": \"DummyImage\"                            }                        ],                        \"FieldType\": 6,                        \"XPath\": \"tcm:Content/custom:Story/custom:multimedia\"                    }                },                \"MetadataFields\": {},                \"ComponentType\": 1,                \"Folder\": {                    \"PublicationId\": \"tcm:0-30-1\",                    \"Id\": \"tcm:30-205-2\",                    \"Title\": \"Stories\"                },                \"Categories\": [],                \"Version\": 4,                \"Publication\": {                    \"Id\": \"tcm:0-30-1\",                    \"Title\": \"600 Urdu Website\"                },                \"OwningPublication\": {                    \"Id\": \"tcm:0-30-1\",                    \"Title\": \"600 Urdu Website\"                },                \"Id\": \"tcm:30-976\",                \"Title\": \"Story - Make my future mine\"            },            \"ComponentTemplate\": {                \"OutputFormat\": \"HTML Fragment\",                \"RevisionDate\": \"2015-01-11T14:10:03\",                \"MetadataFields\": {                    \"viewName\": {                        \"Name\": \"viewName\",                        \"Values\": [                            \"story_4column\"                        ],                        \"FieldType\": 0                    },                    \"gtmView\": {                        \"Name\": \"gtmView\",                        \"Values\": [                            \"GoogleTagManager\"                        ],                        \"FieldType\": 0                    }                },                \"Folder\": {                    \"PublicationId\": \"tcm:0-30-1\",                    \"Id\": \"tcm:30-172-2\",                    \"Title\": \"DD4T Views\"                },                \"Publication\": {                    \"Id\": \"tcm:0-30-1\",                    \"Title\": \"600 Urdu Website\"                },                \"OwningPublication\": {                    \"Id\": \"tcm:0-12-1\",                    \"Title\": \"200 Master Templates\"                },                \"Id\": \"tcm:30-828-32\",                \"Title\": \"Story [4 Column]\"            },            \"IsDynamic\": false,            \"OrderOnPage\": 0        }    ],    \"StructureGroup\": {        \"PublicationId\": \"tcm:0-30-1\",        \"Id\": \"tcm:30-13-4\",        \"Title\": \"Root\"    },    \"Categories\": [],    \"Version\": 1,    \"Publication\": {        \"Id\": \"tcm:0-30-1\",        \"Title\": \"600 Urdu Website\"    },    \"OwningPublication\": {        \"Id\": \"tcm:0-30-1\",        \"Title\": \"600 Urdu Website\"    },    \"Id\": \"tcm:30-984-64\",    \"Title\": \"Story\"}";
	static String test3 = "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";
	static String testPage2 = "H4sIAAAAAAAEANVYbW+jRhD+K4hPrWQcXgy2+VLVeWlOzZtimqt0OlVrmNh7hcWC5XJu5P/eWcCwi53kUqeNK1mJPDM7+8zsM49hH/Vb+EpzmrITwkH3ddu0BobpGZYdWKbvuL416lvuUO/pZzQGRhIRlIf4/YLk/KaYxTRfQFSvNk3TMspPYJp++cHIGzKHAJJlXAY9lplOv3FgYl9cteBJjGFPIbECy/OdsW+b/dFIILkETiLCyRmFOMpFxq8UHq5KbI969b819fQ7EhclbE5YRLJoY8Kln1rj555+VSSQ0bBxokmACWgCsu2Csj8hOk6TZcqAcdlVQgpWS9zO7Om/wuohzaLSt16jN40jyATIsnEh4Vjuhwih8TDxTcNyDAvBNRb8bg8MG00B5bEoQbRS2/Qy1zGnlEkk3s62WeqapnacslzUqE0gntMi0X44vfpRZLl+YJTNn841UlLZmOoEcjpnYq0C17aQOiMpdrrp73rHwaGtaeNNBjn+K3cXHXtsXQLM99PtGUbbvuMJRo9cBwOn4QISIpLfpik/jSHBvWr65MUMGwHzNFt1T8R1DaVAKXJdM6AkJVMJyVQyHpNM4aH4/i9Q8Pcbwhc1eDx9HDp+FBY5TxNfKnFjqjEqvO3pCyARskOqZWNpy/m4gAy0KNVWaaE9EMY1nmozwL8RWf2kVPpC6Ls3oS2u0wekVUFiRWNKQ9uFhiKeUrFk3r+6NxgF0wusoW8OfXvQH5vDF0ahYBHcUwZRdxDssTIId1U35Bl4aeabE7osYo71R7TE8FuGXdaPaIJqlx8tKWFpQpjh/IE7V33sf1mKA7rENVUOvQw++rKEef1bVaNvV9drur8+lXVK/8JoxzKdQU//SCPBF8R1DnS+QP0xBeqKJ3RzQHeQVSmsrkB4smKL/TUEoK9VXrqv5WXDtg4tKbtPs6TUzSpzw8+upyXqBBieKc8VmjbGdx/BbeCdmuEbz8iHNkoqesvVVi1X+yY1niYziCKIpOGMMTpADBKixtQiETk1YdN+Vo5Atr/7KXQ7+cn6vHFJJXVORng6te8QSG+3QHoHIpDVM+dg7LvD/th56VlhVs3N1pPbQJHHerr+gT5ar9Aez9veErnU0R7vjanQpcFamo3nOgd1zEWVQ22fpbRvk04r91sr5Qz2LWebxsssjYqQTyGGkKeZxOiu57DlZfKEvEzeX146jXxLdRnuVpfhIamLa/oDpz8eH7K6OF11Ge5Ql8ne6vI8E/5v4rItEN17gD1Pxe4+b8qn0nkdbdNKFzDXBV8W/KxUP1xyHlxeaGcZmYvmfcdFjGvhj+JeFzEtxnN82QL1TXjbe6BXM45yNdNsetD3M+7YcOzddNEWVbfFkvxkxUhCQ92/J3EOCCBD1zUT90/ibQj7OeUZsrzI4JcsLZav6tzANgYShluYZ5ALxmHr8lLDnlOk/6ajr8mlauTY8OTq8lBf/w05djnIZxUAAA==";
	static String testPage = "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";

	public static void main (String[] args) throws IOException, XMLStreamException, SerializationException {

		// Load Spring
		ApplicationContext context = new FileSystemXmlApplicationContext("dd4t-test/target/classes/application-context.xml");



//		System.out.println(testXml + xml2 + xml3);
		String completeXml = FileUtils.readFileToString(new File("dd4t-test/target/classes/xml-without-java-xslt.xml"));
//
		String homepage =FileUtils.readFileToString(new File("dd4t-test/target/classes/homepage.json"));
		//System.out.println(completeXml);
		//deserializeXmlJackson(completeXml);
		deserializeJson(homepage);


	}

	private static void deserializeXmlJackson (final String completeXml) throws IOException {
		JacksonXmlModule module = new JacksonXmlModule();
		ObjectMapper mapper = new XmlMapper(module);
		mapper.registerModule(configureXmlMapper());
		PageImpl page = mapper.readValue(completeXml, PageImpl.class);

		System.out.println(page.getTitle());
	}

	protected static SimpleModule configureXmlMapper () {
		// tODO: to concrete basefield and customizable CT impls and into DataBinder
		final XmlBaseFieldsDeserializer<BaseField> xmlBaseFieldsDeserializer = new XmlBaseFieldsDeserializer<>(BaseField.class);
		final ComponentPresentationDeserializer componentPresentationDeserializer = new ComponentPresentationDeserializer(ComponentPresentationImpl.class, ComponentTemplateImpl.class, ComponentImpl.class);
		final XmlValuesDeserializer<List> xmlValuesDeserializer = new XmlValuesDeserializer<>(List.class);
		final SimpleModule module = new SimpleModule();
		module.addDeserializer(BaseField.class, xmlBaseFieldsDeserializer);
		module.addDeserializer(ComponentPresentation.class,componentPresentationDeserializer);
		module.addDeserializer(List.class,xmlValuesDeserializer);
		return module;

	}

	// TODO: OrderOnPage always is 0 in the JSon

	private static void deserializeJson (String content) throws IOException, SerializationException {
		String content1 = decodeAndDecompressContent(test3);//test4;//

		PageImpl page = DataBindFactory.buildPage(content1, PageImpl.class);
		System.out.println(content1);
		System.out.println("Page Title: " + page.getTitle());
	}

	private static String decodeAndDecompressContent (String content) throws IOException {
		byte[] decoded;
		if (Base64.isBase64(content)) {
			System.out.println(">> length before base64 decode: " + content.getBytes("UTF-8").length);

			decoded = Base64.decodeBase64(content);
			System.out.println(">> length after base64 decode: " + decoded.length);
		} else {
			decoded = content.getBytes();
		}

		String r = decompress(decoded);

		System.out.println(">> length after decompress: " + r.getBytes("UTF-8").length);
		System.out.println("Content is: " + r);
		return r;
	}

	public static String decompress (byte[] bytes) throws IOException {
		GZIPInputStream gis = null;
		String result = null;

		try {
			gis = new GZIPInputStream(new ByteArrayInputStream(bytes));
			result = IOUtils.toString(gis);
		} finally {
			IOUtils.closeQuietly(gis);
		}
		return result;
	}

	static String[] splitBuffer (String input, int maxLength) {
		int elements = (input.length() + maxLength - 1) / maxLength;
		String[] ret = new String[elements];
		for (int i = 0; i < elements; i++) {
			int start = i * maxLength;
			ret[i] = input.substring(start, Math.min(input.length(), start + maxLength));
		}
		return ret;
	}
}
