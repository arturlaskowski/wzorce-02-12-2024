package wzorce.creational.factory.amethod.solution;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
class UserAccountLombok {
    private final String username;
    private final String email;
    private boolean isPremium;
    private boolean isBusiness;
}
