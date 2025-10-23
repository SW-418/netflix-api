package samwells.io.netflix_api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import samwells.io.netflix_api.dto.request.history.UserHistoryRequest;
import samwells.io.netflix_api.dto.response.CursorPaginatedResponse;
import samwells.io.netflix_api.dto.response.history.WatchHistoryResponse;
import samwells.io.netflix_api.model.history.PaginatedWatchHistory;
import samwells.io.netflix_api.service.history.HistoryService;
import samwells.io.netflix_api.util.UserUtil;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/watch-history")
public class HistoryController {
    private final HistoryService historyService;

    @PostMapping
    ResponseEntity<Void> addHistory(@RequestBody @Valid UserHistoryRequest userHistoryRequest){
        Long userId = UserUtil.getUserId();

        historyService.addHistory(userHistoryRequest.mediaId(), userId);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    CursorPaginatedResponse<List<WatchHistoryResponse>> getHistory(
            @RequestParam(required = false, defaultValue = "5") int size,
            @RequestParam(required = false) String cursor
    ) {
        Long userId = UserUtil.getUserId();
        PaginatedWatchHistory watchHistory = historyService.getWatchHistory(userId, size, cursor);

        return new CursorPaginatedResponse<>(
                watchHistory.watchHistory().stream().map(WatchHistoryResponse::new).toList(),
                watchHistory.cursor()
        );
    }
}
