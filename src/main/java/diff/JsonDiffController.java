package diff;

import diff.utils.FileUtils;
import diff.utils.StringUtils;
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
@RequestMapping("/v1/diff/{id}")
public class JsonDiffController {

    /**
     * EndPoint Responsible for comparing the files included.
     *
     * This method is ready to receive the ID of the file
     *
     * Process:
     * Get the left and rightbjson files included in the last process by the ID
     * get the content of these files recovered
     * decode the content informed in the request
     * compare both string and return the result
     *
     * Results:
     * EQUALS, ADDED and REMOVED
     *
     * Treated Exceptions:
     * CONTENT NOT FOUND and FILE NOT FOUND
     *
     * @param id
     * @return Result of the comparison
     * @throws IOException
     */
    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody
    Result compare(@PathVariable String id) throws IOException {
        String out;

        out = new Differ(id).invoke();

        FileUtils.deleteComparedFiles(id);

        return new Result(out);
    }

}
