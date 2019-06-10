package uk.ac.ebi.pride.integration.message.service.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.Assert;
import uk.ac.ebi.pride.archive.dataprovider.project.SubmissionType;
import uk.ac.ebi.pride.integration.message.model.FileType;
import uk.ac.ebi.pride.integration.message.model.IndexType;
import uk.ac.ebi.pride.integration.message.model.impl.FileGenerationPayload;
import uk.ac.ebi.pride.integration.message.model.impl.IncomingSubmissionPayload;
import uk.ac.ebi.pride.integration.message.model.impl.IndexCompletionPayload;
import uk.ac.ebi.pride.integration.message.service.MessageNotifier;

import java.util.Calendar;

/**
 * RedisMessageNotifier generates redis backed notification messages
 */
public class RedisMessageNotifier implements MessageNotifier<String> {

    private RedisConnectionFactory redisConnectionFactory;

    /**
     * Constructor, sets the Redis connection factory
     * @param redisConnectionFactory the Redis connection factory
     */
    public RedisMessageNotifier(RedisConnectionFactory redisConnectionFactory) {
        Assert.notNull(redisConnectionFactory, "Redis connection factory cannot be null");
        this.redisConnectionFactory = redisConnectionFactory;
    }

    /**
     * Send a new submission notification to a given queue
     * @param queue submission queue
     * @param payload pay load
     */
    public <T> void sendNotification(String queue, T payload, Class<T> payloadClass) {
        RedisTemplate<String, T> submissionRedisTemplate = getRedisTemplate(redisConnectionFactory, payloadClass);
        ListOperations<String, T> submissionList = submissionRedisTemplate.opsForList();
        submissionList.leftPush(queue, payload);
    }

    /**
     * Create an redis template from a given redis connection factory and a given message type
     */
    private <P> RedisTemplate<String, P> getRedisTemplate(RedisConnectionFactory redisConnectionFactory, Class<P> type) {
        RedisTemplate<String, P> publicationRedisTemplate = new RedisTemplate<>();
        publicationRedisTemplate.setConnectionFactory(redisConnectionFactory);
        publicationRedisTemplate.setKeySerializer(new StringRedisSerializer());
        publicationRedisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(type));
        publicationRedisTemplate.afterPropertiesSet(); // must call this before use
        return publicationRedisTemplate;
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/spring/integration/redis-context.xml");
        RedisConnectionFactory connectionFactory = context.getBean("redisConnectionFactory", RedisConnectionFactory.class);
        RedisMessageNotifier messageGenerator = new RedisMessageNotifier(connectionFactory);
        int arg = Integer.parseInt(args[0]);
        switch (arg) {
            case 1:
                messageGenerator.sendNotification("archive.incoming.submission.queue",
                        new IncomingSubmissionPayload(args[1], SubmissionType.COMPLETE, Calendar.getInstance().getTime()),
                        IncomingSubmissionPayload.class);
                break;
            case 2:
                messageGenerator.sendNotification("archive.mztab.completion.queue",
                        new FileGenerationPayload(args[1], FileType.MZTAB),
                        FileGenerationPayload.class);
                break;
            case 3:
                messageGenerator.sendNotification("archive.mgf.completion.queue",
                        new FileGenerationPayload(args[1], FileType.MGF),
                        FileGenerationPayload.class);
                break;
            case 4:
                messageGenerator.sendNotification("archive.post.submission.completion.queue",
                        new IndexCompletionPayload(args[1], IndexType.SPECTRUM),
                        IndexCompletionPayload.class);
                break;
            case 5:
                messageGenerator.sendNotification("archive.post.submission.completion.queue",
                        new IndexCompletionPayload(args[1], IndexType.PROTEIN),
                        IndexCompletionPayload.class);
                break;
            case 6:
                messageGenerator.sendNotification("archive.post.submission.completion.queue",
                        new IndexCompletionPayload(args[1], IndexType.PSM),
                        IndexCompletionPayload.class);
                break;
            case 7:
                messageGenerator.sendNotification("archive.incoming.submission.queue",
                        new IncomingSubmissionPayload(args[1], SubmissionType.PARTIAL, Calendar.getInstance().getTime()),
                        IncomingSubmissionPayload.class);
                break;
            default:
                break;

        }
    }
}
