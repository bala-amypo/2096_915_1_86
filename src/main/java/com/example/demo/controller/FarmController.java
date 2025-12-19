@RestController
@RequestMapping("/farms")
public class FarmController {

    private final FarmService farmService;
    private final UserService userService;

    public FarmController(FarmService f, UserService u) {
        this.farmService = f;
        this.userService = u;
    }

    @PostMapping
    public ResponseEntity<Farm> createFarm(
            @RequestBody FarmRequest r,
            Authentication auth) {

        Long userId = (Long) auth.getPrincipal();

        Farm farm = Farm.builder()
                .name(r.getName())
                .soilPH(r.getSoilPH())
                .waterLevel(r.getWaterLevel())
                .season(r.getSeason())
                .build();

        return ResponseEntity.ok(
                farmService.createFarm(farm, userId));
    }

    @GetMapping
    public ResponseEntity<List<Farm>> listFarms(Authentication auth) {
        Long userId = (Long) auth.getPrincipal();
        return ResponseEntity.ok(
                farmService.getFarmsByOwner(userId));
    }
}
