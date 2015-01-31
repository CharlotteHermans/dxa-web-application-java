package org.dd4t.core.factories;

import org.dd4t.contentmodel.Page;
import org.dd4t.core.exceptions.FactoryException;
import org.dd4t.core.exceptions.ItemNotFoundException;
import org.dd4t.core.exceptions.SerializationException;

public interface PageFactory extends Factory {

    /**
     * Get a page by its URI. No security available; the method will fail if a
     * SecurityFilter is configured on the factory.
     *
     * @param uri of the page
     * @return a Page Object
     * @throws FactoryException
     */
    Page getPage(String uri) throws FactoryException;

    /**
     * Find page by its URL. The url and publication id are specified. No
     * security available; the method will fail if a SecurityFilter is
     * configured on the factory.
     *
     * @return a Page Object
     * @throws org.dd4t.core.exceptions.FactoryException
     */
    Page findPageByUrl(String url, int publicationId) throws FactoryException;

    /**
     * Find the source of the Page by Url. The url and publication id are specified.
     *
     * @return XML as string
     * @throws FactoryException
     */
    String findSourcePageByUrl(String url, int publicationId) throws FactoryException;
    /**
     * Deserializes a JSON encoded String into an object of the given type, which must
     * derive from the Page interface
     *
     * @param source String representing the JSON encoded object
     * @param clazz  Class representing the implementation type to deserialize into
     * @return the deserialized object
     */
    <T extends Page> T deserialize (final String source, final Class<? extends T> clazz) throws FactoryException;
    /**
     * Method to check whether a page exists in the Tridion Broker.
     *
     * @param url the URL to check
     * @param publicationId the publication Id for the url
     * @return true if the page url exists in the broker
     * @throws SerializationException
     * @throws ItemNotFoundException
     */
    Boolean isPagePublished(String url, int publicationId);
}


