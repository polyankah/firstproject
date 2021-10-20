package pimenova.firstproject;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
public class ApiController {
    private List<String> themes = new ArrayList<>();
    /* curl http://localhost:8080/themes -H 'Content-Type:
  text/plain' */
    @GetMapping("themes")
    public List<String> getThemes() {
        return themes;
    }
    /* curl -X POST http://localhost:8080/themes -H 'Content-Type:
   text/plain' -d "TEXT" */
    @PostMapping("themes")
    public void addTheme(@RequestBody String text) {
        themes.add(text);
    }
    /* curl http://localhost:8080/themes/INDEX -H 'Content-Type:
   text/plain' */
    @GetMapping("themes/{index}")
    public ResponseEntity<String> getTheme(@PathVariable("index") Integer index) {
        try {
            return ResponseEntity.ok(themes.get(index));
        } catch (IndexOutOfBoundsException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    /* curl -X DELETE http://localhost:8080/themes/INDEX -H 'Content-Type:
   text/plain' */
    @DeleteMapping("themes/{index}")
    public ResponseEntity<String> deleteText(@PathVariable("index") Integer index) {
        try {
            return ResponseEntity.ok(themes.remove((int) index));
        } catch (IndexOutOfBoundsException ex) {
            return ResponseEntity.notFound().build();
        }
    }
    /* curl -X POST http://localhost:8080/themes/INDEX -H 'Content-Type:
   text/plain' -d "TEXT" */
    @PostMapping("themes/{index}")
    public ResponseEntity<String> updateThemes(
            @PathVariable("index") Integer i,
            @RequestBody String theme) {
        try {
            return ResponseEntity.ok(themes.set(i, theme));
        } catch (IndexOutOfBoundsException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("themes/count")
    /* curl http://localhost:8080/themes/count -H 'Content-Type:
   text/plain' */
    public int getThemesCount() {
        return themes.size();
    }
    /* curl -X POST http://localhost:8080/themes/INDEX/create -H 'Content-Type:
   text/plain' -d "TEXT" */
    @PostMapping("themes/{index}/create")
    public void createTheme(
            @PathVariable("index") Integer i,
            @RequestBody String theme) {
        themes.add(i, theme);
    }

}