package cn.dx.abstractfactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author gudongxian
 * @date 2022/1/7
 */
@Slf4j
public class App implements Runnable {
    private final Kingdom kingdom = new Kingdom();

    public Kingdom getKingdom() {
        return kingdom;
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    @Override
    public void run() {
        log.info("精灵王国");
        createKingdom(Kingdom.FactoryMaker.KingdomType.ELF);
        log.info(getKingdom().getArmy().getDescription());
        log.info(getKingdom().getCastle().getDescription());
        log.info(getKingdom().getKing().getDescription());

        log.info("兽人王国");
        createKingdom(Kingdom.FactoryMaker.KingdomType.ORC);
        log.info(getKingdom().getArmy().getDescription());
        log.info(getKingdom().getCastle().getDescription());
        log.info(getKingdom().getKing().getDescription());
    }

    private void createKingdom(Kingdom.FactoryMaker.KingdomType kingdomType) {
        KingdomFactory kingdomFactory = Kingdom.FactoryMaker.makeFactory(kingdomType);
        kingdom.setKing(kingdomFactory.createKing());
        kingdom.setArmy(kingdomFactory.createArmy());
        kingdom.setCastle(kingdomFactory.createCastle());
    }
}
