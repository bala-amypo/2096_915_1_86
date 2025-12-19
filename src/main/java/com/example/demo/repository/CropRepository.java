public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
    List<Suggestion> findByFarmId(Long farmId);
}
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {
    List<Fertilizer> findByCropName(String cropName);
}

public interface FarmRepository extends JpaRepository<Farm, Long> {
    List<Farm> findByOwnerId(Long ownerId);
}



public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
public interface CropRepository extends JpaRepository<Crop, Long> {
    List<Crop> findSuitableCrops(Double ph, String season);
}