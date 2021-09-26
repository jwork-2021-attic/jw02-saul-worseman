package S191220072;
import java.util.*;
public class MonsterArray {
    static ArrayList<Monster> monsters = new ArrayList<>();
    static{
        boolean[][][] existed = new boolean[4][4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++)
                for(int k = 0; k < 4; k++)
                    existed[i][j][k] = false;
        } 
        int randomR ; 
        int randomG ;
        int randomB ;
        while(monsters.size() < 50){
            randomR = (int)(Math.random() * 4);  // 0 to 255
            randomG = (int)(Math.random() * 4);
            randomB = (int)(Math.random() * 4);
            if(!existed[randomR][randomG][randomB]){
                monsters.add(new Monster(randomR * 85, randomG * 85, randomB * 85));
                existed[randomR][randomG][randomB] = true;
            }
        }
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                for(int k = 0; k < 4; k++){
                    if(!existed[i][j][k]){
                        monsters.add(new Monster(i * 85, j * 85, k * 85));
                        existed[i][j][k] = true;
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
