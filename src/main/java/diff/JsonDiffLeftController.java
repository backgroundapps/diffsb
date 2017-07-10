package diff;

import diff.utils.JSONUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by Samsung on 08/07/2017.
 */
@Controller
@RequestMapping("/v1/diff/{id}/left")
public class JsonDiffLeftController {

    /**
     * EndPoint Responsible for receive the first file to compare.
     *
     * This method is ready to receive the ID of the file and the JSON needs to be encoded in base64
     *
     * Process:
     * Create a Json file where the ID is the name and the content is the encodedJson (decoded)
     *
     * @param id
     * @param encodedJson in base64
     * @return HttpStatus.OK
     * @throws IOException
     */
    @RequestMapping(method= RequestMethod.GET, value = "/{encodedJson}")
    public @ResponseBody
    Result left(@PathVariable String id, @PathVariable String encodedJson) throws IOException {
        JSONUtils.buildFromEncodedJSONFile(id + "_left", encodedJson);
        return new Result(HttpStatus.OK.toString());
    }

}
