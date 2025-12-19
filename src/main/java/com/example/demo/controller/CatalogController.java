@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService c) {
        this.catalogService = c;
    }

    @PostMapping("/crop")
    public ResponseEntity<?> addCrop(
            @RequestBody CropRequest r,
            Authentication auth) {

        boolean admin = auth.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!admin) return ResponseEntity.status(403).build();

        Crop crop = Crop.builder()
                .name(r.getName())
                .suitablePHMin(r.getSuitablePHMin())
                .suitablePHMax(r.getSuitablePHMax())
                .requiredWater(r.getRequiredWater())
                .season(r.getSeason())
                .build();

        return ResponseEntity.ok(catalogService.addCrop(crop));
    }

    @PostMapping("/fertilizer")
    public ResponseEntity<?> addFertilizer(
            @RequestBody FertilizerRequest r,
            Authentication auth) {

        boolean admin = auth.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!admin) return ResponseEntity.status(403).build();

        Fertilizer f = Fertilizer.builder()
                .name(r.getName())
                .npkRatio(r.getNpkRatio())
                .recommendedForCrops(r.getRecommendedForCrops())
                .build();

        return ResponseEntity.ok(catalogService.addFertilizer(f));
    }

    @GetMapping("/crops")
    public ResponseEntity<List<Crop>> findCrops(
            Double ph, Double water, String season) {
        return ResponseEntity.ok(
                catalogService.findSuitableCrops(ph, water, season));
    }

    @GetMapping("/fertilizers")
    public ResponseEntity<List<Fertilizer>> findFerts(String crop) {
        return ResponseEntity.ok(
                catalogService.findFertilizersForCrops(List.of(crop)));
    }
}
