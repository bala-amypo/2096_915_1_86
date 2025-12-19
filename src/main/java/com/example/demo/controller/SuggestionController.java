@RestController
@RequestMapping("/suggestions")
public class SuggestionController {

    private final SuggestionService service;

    public SuggestionController(SuggestionService s) {
        this.service = s;
    }

    @PostMapping("/{farmId}")
    public ResponseEntity<Suggestion> generate(@PathVariable Long farmId) {
        return ResponseEntity.ok(service.generateSuggestion(farmId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Suggestion> getSuggestion(@PathVariable Long id) {
        return ResponseEntity.ok(service.getSuggestion(id));
    }
}
