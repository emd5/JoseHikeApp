package model.Exit;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import controller.HikeController;
import model.Hike;

/**
 * This class saves data to a file
 */
public class ExitData {

    private final ObjectMapper objectMapper;

    /**
     * this is the constructor for ExitData
     *
     * @param objectMapper the objectmapper that maps data into JsonNodes
     * @author Liz Mahoney
     * @author Jacob Langham
     * @version 1.0
     */
    public ExitData(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * this saves data to a file
     *
     * @param hikeList the list of hikes to save
     * @param saveFile the name of the file path
     */
    public void exit(final List<Hike> hikeList, final String saveFile) {
        final Hike hike = new Hike("first hike", "firstHikeLoc", LocalDate.now());

        hike.setDuration(500);
        //hike.setFitness(50, 1000);
        HikeController.getInstance().addHike(hike);

        final JsonNode node = objectMapper.convertValue(hikeList, JsonNode.class);
        try {
            saveJsonNodeToFile(node, saveFile);
        } catch (final IOException e) {
            System.out.println("something went wrong in exit ");
            e.printStackTrace();
        }
    }

    private void saveJsonNodeToFile(final JsonNode data, final String saveFile) throws IOException {
        final ObjectWriter writer = objectMapper.writer();
        writer.writeValue(new File(saveFile), data);
    }
}
