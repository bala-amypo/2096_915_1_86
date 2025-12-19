@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwt;

    public AuthController(UserService u, JwtTokenProvider j) {
        this.userService = u;
        this.jwt = j;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest r) {
        User u = User.builder()
                .name(r.getName())
                .email(r.getEmail())
                .password(r.getPassword())
                .build();
        return ResponseEntity.ok(userService.register(u));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest r) {
        User u = userService.findByEmail(r.getEmail());
        if (u == null) return ResponseEntity.status(401).build();

        String token = jwt.createToken(u.getId(), u.getEmail(), u.getRole());
        return ResponseEntity.ok(
                new AuthResponse(token, u.getId(), u.getEmail(), u.getRole()));
    }
}
