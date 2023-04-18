package racingcar.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import racingcar.web.dto.HistoryDto;
import racingcar.web.dto.ResultDto;
import racingcar.web.dto.UserInputDto;
import racingcar.web.service.RacingGameService;

import java.util.List;

@Controller
public class RacingCarController {

    private final RacingGameService racingGameService;

    public RacingCarController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    @ResponseBody
    public ResponseEntity<ResultDto> racingGame(@RequestBody UserInputDto inputDto) {
        final ResultDto resultDto = racingGameService.getResult(inputDto);

        return ResponseEntity.ok(resultDto);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<HistoryDto>> historyInquiry(){
        final List<HistoryDto> histories = racingGameService.getHistory();
        return ResponseEntity.ok(histories);
    }
}
