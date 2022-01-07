package cn.dx.abstractfactory;

/**
 * @author gudongxian
 * @date 2022/1/7
 */
public interface KingdomFactory {
    Army createArmy();

    Castle createCastle();

    King createKing();

}
