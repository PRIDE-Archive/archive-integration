package uk.ac.ebi.pride.integration.message.service;

/**
 * MessageNotifier is an interface to sending message notification
 * Q: represents the address where the message will be sent
 */
public interface MessageNotifier<Q> {

    /**
     * Sends a message notification.
     * @param queue the queue to send the message to
     * @param payload the payload information
     * @param payloadClass the payload class
     * @param <T> generic type
     */
    public <T> void sendNotification(Q queue, T payload, Class<T> payloadClass);
}
