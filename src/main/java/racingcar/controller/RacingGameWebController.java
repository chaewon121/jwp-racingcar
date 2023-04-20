package racingcar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import racingcar.dto.web.ResultDto;
import racingcar.dto.web.UserInputDto;
import racingcar.service.RacingGameService;

import java.util.List;

@Controller
public class RacingGameWebController {

    private final RacingGameService racingGameService;

    public RacingGameWebController(RacingGameService racingGameService) {
        this.racingGameService = racingGameService;
    }

    @PostMapping("/plays")
    @ResponseBody
    public ResponseEntity<ResultDto> racingGame(@RequestBody UserInputDto inputDto) {
        final ResultDto resultDto = racingGameService.getResult(inputDto);

        return ResponseEntity.ok(resultDto);
    }

    @GetMapping("/plays")
    public ResponseEntity<List<ResultDto>> historyInquiry() {
        final List<ResultDto> histories = racingGameService.getHistory();
        return ResponseEntity.ok(histories);
    }
}
