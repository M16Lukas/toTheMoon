package com.theMoon.moon.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

import com.theMoon.moon.vo.Feed;
import com.theMoon.moon.vo.FeedMessage;

public class RSSFeedParser {
	static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String CHANNEL = "channel";
    static final String LANGUAGE = "language";
    static final String COPYRIGHT = "copyright";
    static final String LINK = "link";
    static final String SOURCE = "source";
    static final String ITEM = "item";
    static final String PUB_DATE = "pubDate";
    static final String GUID = "guid";
    static final String PUBDATE ="pubdate";

    final URL url;
    
    public RSSFeedParser(String feedUrl) {
    	try {
			this.url = new URL(feedUrl);
		} catch (MalformedURLException e) {
			throw new RuntimeException();
		}
    }
    
    public Feed readFeed() {
		Feed feed = null;
		try {
			boolean isFeedHeader = true;
			
			// set header values intial to the empty String
			String description = "";
			String title = "";
			String link = "";
			String language = "";
			String copyright = "";
			String pubDate = "";
			
			// message
			String guid = "";
			String source = "";
			
			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			
			// Setup a new eventReader
			InputStream in = read();
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			
			// read the XML document
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) {
					String localPart = event.asStartElement().getName().getLocalPart();
					
					switch (localPart) {
					case ITEM:
						if(isFeedHeader) {
							isFeedHeader = false;
							feed = new Feed(title, link, description, language, copyright, pubDate);
						}
						event = eventReader.nextEvent();
						break;
					case TITLE:
						title = getCharacterData(event, eventReader);
						break;
					case DESCRIPTION:
						description = getCharacterData(event, eventReader);
						break;
					case LINK:
						link = getCharacterData(event, eventReader);
						break;
					case GUID:
						guid = getCharacterData(event, eventReader);
						break;
					case LANGUAGE:
						language = getCharacterData(event, eventReader);
						break;
					case PUB_DATE:
						pubDate = getCharacterData(event, eventReader);
						break;
					case COPYRIGHT:
						copyright = getCharacterData(event, eventReader);
						break;
					case PUBDATE:
						pubDate = getCharacterData(event, eventReader);
						break;
					}
				} else if (event.isEndElement()) {
					if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
						FeedMessage message = new FeedMessage();
						message.setTitle(title);
						message.setLink(link);
						message.setGuid(guid);
						message.setPubDate(pubDate);
						message.setDescription(description);
						message.setSource(source);
						feed.getMessages().add(message);
						event = eventReader.nextEvent();
						continue;
					}
				}
			}
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
		
		return feed;
	}
    
    private String getCharacterData(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException{
    	String result = "";
    	event = eventReader.nextEvent();
    	if (event instanceof Characters) {
			result = event.asCharacters().getData();
		}
    	return result;
    }
    
    private InputStream read() {
    	try {
			return url.openStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
    }
}
