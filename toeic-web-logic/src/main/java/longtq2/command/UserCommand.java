package longtq2.command;

import longtq2.core.dto.UserDTO;
import longtq2.core.web.command.AbstractCommand;

public class UserCommand extends AbstractCommand<UserDTO> {
    public UserCommand() {
        this.pojo = new UserDTO();
    }

}
