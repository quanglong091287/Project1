package longtq2.command;

import longtq2.core.dto.ListenGuidelineDTO;
import longtq2.core.web.command.AbstractCommand;

public class ListenGuidelineCommand extends AbstractCommand<ListenGuidelineDTO>{
    public ListenGuidelineCommand(){
        this.pojo = new ListenGuidelineDTO();
    }
}

