package diff;

import diff.utils.FileUtils;
import diff.utils.JSONUtils;
import diff.utils.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/v1/diff/{id}/right")
public class JsonDiffRigthController {

    private final AtomicLong counter = new AtomicLong();

    /**
     * EndPoint Responsible for receive the second file to compare.
     *
     * This method is ready to receive the ID of the file and the JSON needs to be encoded in base64
     *
     * Process:
     * Check if the left file was included to keep on the flow
     * Create a Json file where the ID is the name and the content is the encodedJson (decoded)
     *
     * @param id
     * @param encodedJson in base64
     * @return HttpStatus.OK or LEFT FILE NOT FOUND
     * @throws IOException
     */
    @RequestMapping(method= RequestMethod.GET, value = "/{encodedJson}")
    public @ResponseBody
    Result right(@PathVariable String id, @PathVariable String encodedJson) throws IOException {

        //Just create the right json if the left one exists.
        if(new File(id + "_left.json").exists()){
            JSONUtils.buildFromEncodedJSONFile(id + "_right", encodedJson);
            return new Result(HttpStatus.OK.toString());

        } else {
            return new Result("LEFT FILE NOT FOUND");
        }
    }

}
