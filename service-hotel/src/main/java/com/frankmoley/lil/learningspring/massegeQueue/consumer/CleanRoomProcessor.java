
package com.frankmoley.lil.learningspring.massegeQueue.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frankmoley.lil.learningspring.data.entity.Room;
import org.slf4j.*;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class CleanRoomProcessor {
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(CleanRoomProcessor.class);

    public CleanRoomProcessor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    public void reciveMessage(String roomJson) {
        logger.info("recived message");
        try{
            Room room = objectMapper.readValue(roomJson, Room.class);
            logger.info("ready for cleaing the room:" + room.getRoomNumber());
        }catch(IOException ex){
            logger.error("exeception error", ex);
        }
    }
}
