package samwells.io.netflix_api.service.security;

public interface JwtService {
    String generateJwt(Long userId);
    boolean validateJwt(String jwt);
}
