import java.awt.*;

public class letterDisplay {
//letterDisplay
    public static int wordLength(Character[] word){
        int len = 0;
        shrtHand stroke;
        for(Character c : word){
            if(letterImgMapping.isVowel(c))
                continue;
            stroke = letterImgMapping.strokeMap.get(c);
            len += stroke.getLength();
        }
        return len;
    }

    public static int wordHeight(Character[] word){
        int min = 0, max = 0;
        int y=0;
        shrtHand stroke;
        for(Character c : word){
            if(letterImgMapping.isVowel(c))
                continue;
            stroke = letterImgMapping.strokeMap.get(c);
            Point start = stroke.getStart();
            Point end = stroke.getEnd();
            y += (end.y-start.y);

            if(y > max){
                max = y;
            }
            if(y < min){
                min = y;
            }
        }
        return (min + max) / 2;
    }
}
