package makosear.datingsim.UI;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import makosear.datingsim.Scene.*;

public class SceneHandler {
    // \n new line
    // $o option
    // | option divider
    // $g +1 affection points
    // $b -1 affection points

    private Map<String, List<Scene>> characterToScenes = new HashMap<>();

    //Itsuki scenes

    Scene itsukiOneHeart = new Scene("Itsuki: Hello.\n" + //
                                    "Itsuki: Isn't it a nice day?\n" + //
                                    "$oWhat do you think?", 
                                    "|Nice|Bad", 
                                    new String[]{"$gItsuki: Right?", "$bItsuki: I see."});

    Scene itsukiTwoHeart = new Scene ("Itsuki: this is the second scene test \n" + //
                                    "$oWhat do you think?",
                                    "|Sure|Lol",
                                    new String[]{"$gItsuki: Right?", "$bItsuki: I see."});

                                    Scene itsukiThreeHeart = new Scene("Itsuki: Third conversation placeholder\n$oYour thoughts?",
                                    "|Interesting|Boring",
                                    new String[]{"$gItsuki: Glad you agree", "$bItsuki: That's harsh"});
    Scene itsukiFourHeart = new Scene("Itsuki: Fourth interaction\n$oChoose response?",
                                    "|Agree|Disagree",
                                    new String[]{"$gItsuki: Wonderful", "$bItsuki: Disappointing"});
    Scene itsukiFiveHeart = new Scene("Itsuki: Final test scene\n$oYour answer?",
                                    "|Accept|Reject",
                                    new String[]{"$gItsuki: Thank you", "$bItsuki: Maybe next time"});

    // Chiaki Scenes
    Scene chiakiOneHeart = new Scene("Chiaki: First meeting\n$oHow respond?",
                                    "|Friendly|Shy",
                                    new String[]{"$gChiaki: You're nice", "$bChiaki: Don't be scared"});
    Scene chiakiTwoHeart = new Scene("Chiaki: Coffee shop encounter\n$oOrder what?",
                                    "|Latte|Espresso",
                                    new String[]{"$gChiaki: Good choice!", "$bChiaki: Too strong?"});
    Scene chiakiThreeHeart = new Scene("Chiaki: Library chat\n$oPick topic?",
                                    "|Books|Weather",
                                    new String[]{"$gChiaki: I love reading", "$bChiaki: It's sunny"});
    Scene chiakiFourHeart = new Scene("Chiaki: Park walk\n$oComment on flowers?",
                                    "|Beautiful|Ordinary",
                                    new String[]{"$gChiaki: They're lovely", "$bChiaki: Not your style?"});
    Scene chiakiFiveHeart = new Scene("Chiaki: Final encounter\n$oSay goodbye?",
                                    "|Warm|Casual",
                                    new String[]{"$gChiaki: Until next time", "$bChiaki: Bye then"});

    // Shu Scenes
    Scene shuOneHeart = new Scene("Shu: Initial meeting\n$oGreeting?",
                                "|Formal|Casual",
                                new String[]{"$gShu: Respectful", "$bShu: Too familiar"});
    Scene shuTwoHeart = new Scene("Shu: Training session\n$oFeedback?",
                                "|Praise|Critique",
                                new String[]{"$gShu: Thanks", "$bShu: Noted"});
    Scene shuThreeHeart = new Scene("Shu: Night patrol\n$oJoin him?",
                                "|Yes|No",
                                new String[]{"$gShu: Keep up", "$bShu: Your loss"});
    Scene shuFourHeart = new Scene("Shu: Weapon maintenance\n$oComment?",
                                "|Skilled|Routine",
                                new String[]{"$gShu: Years of practice", "$bShu: Necessary work"});
    Scene shuFiveHeart = new Scene("Shu: Final test\n$oReady?",
                                "|Always|Never",
                                new String[]{"$gShu: Good", "$bShu: Weak"});

    // Gaku Scenes
    Scene gakuOneHeart = new Scene("Gaku: First encounter\n$oReact to smile?",
                                "|Return smile|Look away",
                                new String[]{"$gGaku: Friendly", "$bGaku: Shy?"});
    Scene gakuTwoHeart = new Scene("Gaku: Music store meet\n$oGenre choice?",
                                "|Rock|Jazz",
                                new String[]{"$gGaku: Great taste", "$bGaku: Not my style"});
    Scene gakuThreeHeart = new Scene("Gaku: Concert invite\n$oResponse?",
                                "|Excited|Hesitant",
                                new String[]{"$gGaku: Let's go", "$bGaku: Not sure?"});
    Scene gakuFourHeart = new Scene("Gaku: Songwriting help\n$oContribute?",
                                "|Lyrics|Melody",
                                new String[]{"$gGaku: Perfect", "$bGaku: Try harder"});
    Scene gakuFiveHeart = new Scene("Gaku: Final performance\n$oCheer?",
                                "|Loud|Subtle",
                                new String[]{"$gGaku: Energy!", "$bGaku: Reserved"});

    // Yato Scenes
    Scene yatoOneHeart = new Scene("Yato: Mysterious meeting\n$oApproach?",
                                "|Direct|Cautious",
                                new String[]{"$gYato: Bold", "$bYato: Wise"});
    Scene yatoTwoHeart = new Scene("Yato: Night encounter\n$oFollow?",
                                "|Trust|Doubt",
                                new String[]{"$gYato: Good", "$bYato: Smart"});
    Scene yatoThreeHeart = new Scene("Yato: Secret reveal\n$oReact?",
                                "|Accept|Question",
                                new String[]{"$gYato: Thanks", "$bYato: Suspicious"});
    Scene yatoFourHeart = new Scene("Yato: Dangerous choice\n$oJoin?",
                                "|Brave|Hesitant",
                                new String[]{"$gYato: Courageous", "$bYato: Careful"});
    Scene yatoFiveHeart = new Scene("Yato: Final truth\n$oBelieve?",
                                "|Yes|No",
                                new String[]{"$gYato: Correct", "$bYato: Wrong"});

    // Tsumugi Scenes
    Scene tsumugiOneHeart = new Scene("Tsumugi: Cafe meeting\n$oOrder?",
                                    "|Cake|Tea",
                                    new String[]{"$gTsumugi: Sweet!", "$bTsumugi: Simple"});
    Scene tsumugiTwoHeart = new Scene("Tsumugi: Art talk\n$oOpinion?",
                                    "|Love|Confused",
                                    new String[]{"$gTsumugi: Me too", "$bTsumugi: Hmm"});
    Scene tsumugiThreeHeart = new Scene("Tsumugi: Museum visit\n$oFavorite era?",
                                    "|Renaissance|Modern",
                                    new String[]{"$gTsumugi: Classic", "$bTsumugi: Bold"});
    Scene tsumugiFourHeart = new Scene("Tsumugi: Cooking demo\n$oTaste?",
                                    "|Delicious|Needs salt",
                                    new String[]{"$gTsumugi: Thanks!", "$bTsumugi: Really?"});
    Scene tsumugiFiveHeart = new Scene("Tsumugi: Final request\n$oHelp?",
                                    "|Gladly|Busy",
                                    new String[]{"$gTsumugi: Kind", "$bTsumugi: Okay"});

    public SceneHandler() {
        characterToScenes.put("Itsuki", List.of(itsukiOneHeart, itsukiTwoHeart, itsukiThreeHeart, itsukiFourHeart, itsukiFiveHeart));
        characterToScenes.put("Chiaki", List.of(chiakiOneHeart, chiakiTwoHeart, chiakiThreeHeart, chiakiFourHeart, chiakiFiveHeart));
        characterToScenes.put("Shu", List.of(shuOneHeart, shuTwoHeart, shuThreeHeart, shuFourHeart, shuFiveHeart));
        characterToScenes.put("Gaku", List.of(gakuOneHeart, gakuTwoHeart, gakuThreeHeart, gakuFourHeart, gakuFiveHeart));
        characterToScenes.put("Yato", List.of(yatoOneHeart, yatoTwoHeart, yatoThreeHeart, yatoFourHeart, yatoFiveHeart));
        characterToScenes.put("Tsumugi", List.of(tsumugiOneHeart, tsumugiTwoHeart, tsumugiThreeHeart, tsumugiFourHeart, tsumugiFiveHeart));
    }

    public List<Scene> getCenas(String character) {
        return characterToScenes.get(character);
    }

}
