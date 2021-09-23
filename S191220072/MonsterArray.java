package S191220072;
import java.util.*;
import S191220072.Line.Position;
public class MonsterArray {
    static ArrayList<Monster> monsters = new ArrayList<>();
    static{
        boolean[] existed = new boolean[255  * 3 + 1];
        for(int i = 0; i < 756; i++){
            existed[i] = false;
        } 
        int randomR ;  // 0 to 255
        int randomG ;
        int randomB ;
        while(monsters.size() < 64){
            randomR = (int)(Math.random() * 256);  // 0 to 255
            randomG = (int)(Math.random() * 256);
            randomB = (int)(Math.random() * 256);
            if(!existed[randomB + randomG + randomR]){
                monsters.add(new Monster(randomR, randomG, randomB));
                existed[randomB + randomG + randomR] = true;
            }
        }
    }
    public static Monster getMonsterByColor(int color) {

        for (Monster monster: monsters) {
            if (monster.color() == color) {
                return monster;
            }
        }
        return null;

    }
    public static Iterator<Monster> iterator(){
        return monsters.iterator();
    }

    public static void main(String[] args){
        for(Monster monster : monsters){
            System.out.println(monster);
        }
    }
}
