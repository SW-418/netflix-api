package samwells.io.netflix_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import samwells.io.netflix_api.dto.request.history.UserHistoryRequest;
import samwells.io.netflix_api.entity.User;
import samwells.io.netflix_api.repository.UserRepository;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class HistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;

    @Test
    void addHistory_withValidMovieRequest_addsWatchHistory() throws Exception {
        User user = new User();
        user.setUsername("test@test.com");
        user.setPassword("blahhhhhh");

        user = userRepository.save(user);
        Long userId = user.getId();

        mockMvc.perform(
                post("/api/v1/watch-history")
                        .content(objectMapper.writeValueAsString(new UserHistoryRequest(1L, null)))
                        .contentType("application/json")
                        .with(jwt().jwt(jwt -> jwt.claim("sub", String.valueOf(userId))))
        )
        .andExpect(status().isOk());
    }

    @Test
    void addHistory_withValidTvShowEpisodeRequest_addsWatchHistory() throws Exception {
        User user = new User();
        user.setUsername("test@test.com");
        user.setPassword("blahhhhhh");

        user = userRepository.save(user);
        Long userId = user.getId();

        mockMvc.perform(
                post("/api/v1/watch-history")
                        .content(objectMapper.writeValueAsString(new UserHistoryRequest(null, 1L)))
                        .contentType("application/json")
                        .with(jwt().jwt(jwt -> jwt.claim("sub", String.valueOf(userId))))
        )
        .andExpect(status().isOk());
    }

    @Test
    void addHistory_withInvalidTvShowRequest_returnsBadRequest() throws Exception {
        User user = new User();
        user.setUsername("test@test.com");
        user.setPassword("blahhhhhh");

        user = userRepository.save(user);
        Long userId = user.getId();

        mockMvc.perform(
                post("/api/v1/watch-history")
                        .content(objectMapper.writeValueAsString(new UserHistoryRequest(null, null)))
                        .contentType("application/json")
                        .with(jwt().jwt(jwt -> jwt.claim("sub", String.valueOf(userId))))
        )
        .andExpect(status().isBadRequest());
    }

    @Test
    void addHistory_withInvalidTvShowRequestWithBothParams_returnsBadRequest() throws Exception {
        User user = new User();
        user.setUsername("test@test.com");
        user.setPassword("blahhhhhh");

        user = userRepository.save(user);
        Long userId = user.getId();

        mockMvc.perform(
                post("/api/v1/watch-history")
                        .content(objectMapper.writeValueAsString(new UserHistoryRequest(1L, 2L)))
                        .contentType("application/json")
                        .with(jwt().jwt(jwt -> jwt.claim("sub", String.valueOf(userId))))
        )
        .andExpect(status().isBadRequest());
    }

    @Test
    void addHistory_withInvalidRequestBodyValue_returnsBadRequest() throws Exception {
        User user = new User();
        user.setUsername("test@test.com");
        user.setPassword("blahhhhhh");

        user = userRepository.save(user);
        Long userId = user.getId();

        mockMvc.perform(
                post("/api/v1/watch-history")
                        .content("""
                                    {
                                        "movieId": abcde
                                    }
                                """)
                        .contentType("application/json")
                        .with(jwt().jwt(jwt -> jwt.claim("sub", String.valueOf(userId))))
        )
        .andExpect(status().isBadRequest());
    }

    @Test
    void addHistory_withNoAuth_returnsUnauthorized() throws Exception {
        User user = new User();
        user.setUsername("test@test.com");
        user.setPassword("blahhhhhh");

        user = userRepository.save(user);

        mockMvc.perform(
                post("/api/v1/watch-history")
                        .content(objectMapper.writeValueAsString(new UserHistoryRequest(null, 2L)))
                        .contentType("application/json")
        )
        .andExpect(status().isUnauthorized());
    }
}