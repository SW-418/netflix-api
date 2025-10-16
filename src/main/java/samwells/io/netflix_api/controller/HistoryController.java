package samwells.io.netflix_api.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import samwells.io.netflix_api.dto.request.history.UserHistoryRequest;
import samwells.io.netflix_api.dto.response.history.WatchHistoryResponse;
import samwells.io.netflix_api.exception.BadRequestException;
import samwells.io.netflix_api.service.history.HistoryService;
import samwells.io.netflix_api.util.UserUtil;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/watch-history")
public class HistoryController {
    private final HistoryService historyService;

    @PostMapping
    ResponseEntity<Void> addHistory(@RequestBody UserHistoryRequest userHistoryRequest){
        Long userId = UserUtil.getUserId();

        validateUserHistoryRequest(userHistoryRequest);

        if (userHistoryRequest.movieId() != null) historyService.addMovieHistory(userHistoryRequest.movieId(), userId);
        if (userHistoryRequest.tvShowEpisodeId() != null) historyService.addTvShowEpisodeHistory(userHistoryRequest.tvShowEpisodeId(), userId);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<List<WatchHistoryResponse>> getHistory(
            @PageableDefault @RequestParam(required = false) Pageable pageable
    ) {
        Long userId = UserUtil.getUserId();
        List<WatchHistoryResponse> watchHistory = historyService
                .getWatchHistory(userId, pageable)
                .stream()
                .map(WatchHistoryResponse::new)
                .toList();

        return ResponseEntity.ok(watchHistory);
    }

    private void validateUserHistoryRequest(UserHistoryRequest userHistoryRequest) {
        if (userHistoryRequest.movieId() == null && userHistoryRequest.tvShowEpisodeId() == null) {
            throw new BadRequestException("Must provide movieId OR tvShowEpisodeId");
        }
        if (userHistoryRequest.movieId() != null && userHistoryRequest.tvShowEpisodeId() != null) {
            throw new BadRequestException("Must provide movieId OR tvShowEpisodeId");
        }
    }
}
