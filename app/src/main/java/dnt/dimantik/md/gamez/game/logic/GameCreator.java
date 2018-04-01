package dnt.dimantik.md.gamez.game.logic;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import dnt.dimantik.md.gamez.game.logic.bd.BDHelper;
import dnt.dimantik.md.gamez.game.logic.clases.GameData;
import dnt.dimantik.md.gamez.game.logic.clases.Player;
import dnt.dimantik.md.gamez.game.logic.clases.location.Location;
import dnt.dimantik.md.gamez.game.logic.clases.location.Place;
import dnt.dimantik.md.gamez.game.logic.clases.location.Way;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Bag;
import dnt.dimantik.md.gamez.game.logic.clases.resource.BodyClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Cartridges;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Drug;
import dnt.dimantik.md.gamez.game.logic.clases.resource.FeetClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.FireArms;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Food;
import dnt.dimantik.md.gamez.game.logic.clases.resource.HeadClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.LegsClothes;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Liquid;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Resource;
import dnt.dimantik.md.gamez.game.logic.clases.resource.SteelArms;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Transport;
import dnt.dimantik.md.gamez.game.logic.clases.resource.Weapon;

import static dnt.dimantik.md.gamez.game.logic.AssertResourceName.*;

class GameCreator {

    private static Map<UUID, Resource> sResourceMap;

    static void createNewGame(GameInterface gameInterface, String playerName){
        BDHelper.deleteGame();
        sResourceMap = new HashMap<>();
        addNewMap(gameInterface);
        addNewResourceList(gameInterface);
        addNewPlayer(gameInterface, playerName);
        Log.i("TAG", "PLAYER BAG SIZE - " + gameInterface.getPlayer().getCurrentBag().getResourceList().size() + "/" + gameInterface.getPlayer().getCurrentBag().getResourceUUIDList().size());
    }

    private static void addNewMap(GameInterface gameInterface){
        Location location1 = Location.createNewLocation(1, "Город Z",
                "Раньше это был огромный мегаполис, теперь же это город мертвых", "");

        List<Resource.ResourceType> resourceTypesFromRoom = new ArrayList<>(2);
        resourceTypesFromRoom.add(Resource.ResourceType.IN_ROOM);
        resourceTypesFromRoom.add(Resource.ResourceType.EVERYWHERE);

        List<Resource.ResourceType> resourceTypesFromOutside = new ArrayList<>(2);
        resourceTypesFromRoom.add(Resource.ResourceType.OUTSIDE);
        resourceTypesFromRoom.add(Resource.ResourceType.EVERYWHERE);

        List<Resource.ResourceType> resourceTypesFromAll = new ArrayList<>(2);
        resourceTypesFromRoom.add(Resource.ResourceType.OUTSIDE);
        resourceTypesFromRoom.add(Resource.ResourceType.EVERYWHERE);
        resourceTypesFromRoom.add(Resource.ResourceType.IN_ROOM);

        Place place11 = Place.createNewPlace(location1.getNewIdFromNewPlace(), "Продуктовый магазин", 1, "Тут должно быть много продуктов", PLACE_MARKET, resourceTypesFromRoom);
        location1.addPlace(place11, true);
        Place place12 = Place.createNewPlace(location1.getNewIdFromNewPlace(), "Заправка", 2, "Может посчастливиться найти машину на ходу", PLACE_GAS_STATION, resourceTypesFromOutside);
        location1.addPlace(place12, true);
        Place place13 = Place.createNewPlace(location1.getNewIdFromNewPlace(), "Больница", 3, "", PLACE_HOSPITAL, resourceTypesFromAll);
        location1.addPlace(place13, true);
        Place place14 = Place.createNewPlace(location1.getNewIdFromNewPlace(), "Полицейский участок", 2, "Вполне возможно что что-то из оружия еще осталось", PLACE_POLICE_STATION, resourceTypesFromAll);
        location1.addPlace(place14, true);
        Place place15 = Place.createNewPlace(location1.getNewIdFromNewPlace(), "Военая база", 2, "Без сомнений тут можно найти немало полезного", PLACE_MILITARY_STATION, resourceTypesFromAll);
        location1.addPlace(place15, true);
        Place place16 = Place.createNewPlace(location1.getNewIdFromNewPlace(), "Гипермаркет", 1, "Тут должно быть много провизии", PLACE_S_E_CENTER, resourceTypesFromRoom);
        location1.addPlace(place16, true);

        /*PlaceLevelTwo place12 = new PlaceLevelTwo("Оруженый магазин", 120, 3, "12");

        List<PlaceLevelThree> list12x = new ArrayList<>();
        PlaceLevelThree place121 = new PlaceLevelThree("Прилавок", 5, 3, "121");
        PlaceLevelThree place122 = new PlaceLevelThree("Склад", 5, 3, "122");
        list12x.add(place121);
        list12x.add(place122);

        place12.setPlaceLevelThreeList(list12x);

        PlaceLevelTwo place13 = new PlaceLevelTwo("Больница", 120, 3, "13");

        List<PlaceLevelThree> list13x = new ArrayList<>();
        PlaceLevelThree place131 = new PlaceLevelThree("Ресепшн", 5, 3, "131");
        PlaceLevelThree place132 = new PlaceLevelThree("Склад с лекарствами", 5, 3, "132");
        PlaceLevelThree place133 = new PlaceLevelThree("Палаты", 5, 3, "133");
        PlaceLevelThree place134 = new PlaceLevelThree("Подземная парковка", 5, 3, "134");
        list13x.add(place131);
        list13x.add(place132);
        list13x.add(place133);
        list13x.add(place134);

        place13.setPlaceLevelThreeList(list13x);

        PlaceLevelTwo place14 = new PlaceLevelTwo("Полицейский участок", 120, 3, "14");

        List<PlaceLevelThree> list14x = new ArrayList<>();
        PlaceLevelThree place141 = new PlaceLevelThree("КПП", 5, 3, "141");
        PlaceLevelThree place142 = new PlaceLevelThree("Камеры", 5, 3, "142");
        PlaceLevelThree place143 = new PlaceLevelThree("Оружейная", 5, 3, "143");
        PlaceLevelThree place144 = new PlaceLevelThree("Парковка", 5, 3, "144");
        list14x.add(place141);
        list14x.add(place142);
        list14x.add(place143);
        list14x.add(place144);

        place14.setPlaceLevelThreeList(list14x);

        PlaceLevelTwo place15 = new PlaceLevelTwo("Заправка", 120, 3, "15");

        List<PlaceLevelThree> list15x = new ArrayList<>();
        PlaceLevelThree place151 = new PlaceLevelThree("Бензоколонка", 5, 3, "151");
        PlaceLevelThree place152 = new PlaceLevelThree("Маркет", 5, 3, "152");
        PlaceLevelThree place153 = new PlaceLevelThree("Туалет", 5, 3, "153");
        PlaceLevelThree place154 = new PlaceLevelThree("Служебное помещение", 5, 3, "154");
        list15x.add(place151);
        list15x.add(place152);
        list15x.add(place153);
        list15x.add(place154);

        place15.setPlaceLevelThreeList(list15x);

        List<PlaceLevelTwo> list1xx = new ArrayList<>();
        list1xx.add(place11);
        list1xx.add(place12);
        list1xx.add(place13);
        list1xx.add(place14);
        list1xx.add(place15);

        place1.setPlaceLevelTwoList(list1xx);

        game.getPlaceLevelOneList().add(place1);*/

//        PlaceLevelOne place2 = new PlaceLevelOne("Пригород", 1440, 0.5, "2");
//
//        PlaceLevelTwo place21 = new PlaceLevelTwo("Пищевая фабрика", 120, 0.3, "21");
//
//        List<PlaceLevelThree> list21x = new ArrayList<>();
//        PlaceLevelThree place211 = new PlaceLevelThree("Конвеер", 5, 0.3, "211");
//        PlaceLevelThree place212 = new PlaceLevelThree("Парковка", 5, 0.3, "212");
//        PlaceLevelThree place213 = new PlaceLevelThree("Туалет", 5, 0.3, "213");
//        PlaceLevelThree place214 = new PlaceLevelThree("Склад", 5, 0.3, "214");
//        PlaceLevelThree place215 = new PlaceLevelThree("Кабинеты персонала", 5, 0.3, "215");
//        list21x.add(place211);
//        list21x.add(place212);
//        list21x.add(place213);
//        list21x.add(place214);
//        list21x.add(place215);
//
//        place21.setPlaceLevelThreeList(list21x);
//
//        PlaceLevelTwo place22 = new PlaceLevelTwo("Заправка", 120, 0.3, "22");
//
//        List<PlaceLevelThree> list22x = new ArrayList<>();
//        PlaceLevelThree place221 = new PlaceLevelThree("Бензоколонка", 5, 0.3, "221");
//        PlaceLevelThree place222 = new PlaceLevelThree("Кафе", 5, 0.3, "222");
//        PlaceLevelThree place223 = new PlaceLevelThree("Служебное помещение", 5, 0.3, "223");
//        PlaceLevelThree place224 = new PlaceLevelThree("Кладовка", 5, 0.3, "224");
//        list22x.add(place221);
//        list22x.add(place222);
//        list22x.add(place223);
//        list22x.add(place224);
//
//        place22.setPlaceLevelThreeList(list22x);
//
//        PlaceLevelTwo place23 = new PlaceLevelTwo("Супермаркет", 120, 0.3, "23");
//
//        List<PlaceLevelThree> list23x = new ArrayList<>();
//        PlaceLevelThree place231 = new PlaceLevelThree("Кассы", 5, 0.3, "231");
//        PlaceLevelThree place232 = new PlaceLevelThree("Отдел продуктов", 5, 0.3, "232");
//        PlaceLevelThree place233 = new PlaceLevelThree("Бытовой отдел", 5, 0.3, "233");
//        PlaceLevelThree place234 = new PlaceLevelThree("Служебное помещение", 5, 0.3, "234");
//        PlaceLevelThree place235 = new PlaceLevelThree("Склад", 5, 0.3, "235");
//        list23x.add(place231);
//        list23x.add(place232);
//        list23x.add(place233);
//        list23x.add(place234);
//        list23x.add(place235);
//
//        place23.setPlaceLevelThreeList(list23x);
//
//        PlaceLevelTwo place24 = new PlaceLevelTwo("Отель", 120, 0.3, "24");
//
//        List<PlaceLevelThree> list24x = new ArrayList<>();
//        PlaceLevelThree place241 = new PlaceLevelThree("Номера бизнес класса", 5, 0.3, "241");
//        PlaceLevelThree place242 = new PlaceLevelThree("Номера эконом класса", 5, 0.3, "242");
//        PlaceLevelThree place243 = new PlaceLevelThree("Номера класса люкс", 5, 0.3, "243");
//        PlaceLevelThree place244 = new PlaceLevelThree("Туалет", 5, 0.3, "244");
//        PlaceLevelThree place245 = new PlaceLevelThree("Ресторан", 5, 0.3, "245");
//        PlaceLevelThree place246 = new PlaceLevelThree("Пищевой склад", 5, 0.3, "246");
//        PlaceLevelThree place247 = new PlaceLevelThree("Подземная парковка", 5, 0.3, "247");
//        list24x.add(place241);
//        list24x.add(place242);
//        list24x.add(place243);
//        list24x.add(place244);
//        list24x.add(place245);
//        list24x.add(place246);
//        list24x.add(place247);
//
//        place24.setPlaceLevelThreeList(list24x);
//
//        PlaceLevelTwo place25 = new PlaceLevelTwo("Дамба", 120, 0.3, "25");
//
//        List<PlaceLevelThree> list25x = new ArrayList<>();
//        PlaceLevelThree place251 = new PlaceLevelThree("Уровень 1", 5, 0.3, "251");
//        PlaceLevelThree place252 = new PlaceLevelThree("Уровень 2", 5, 0.3, "252");
//        PlaceLevelThree place253 = new PlaceLevelThree("Уровень 3", 5, 0.3, "253");
//        PlaceLevelThree place254 = new PlaceLevelThree("Мостик управляющего", 5, 0.3, "254");
//        PlaceLevelThree place255 = new PlaceLevelThree("Туалет", 5, 0.3, "255");
//        PlaceLevelThree place256 = new PlaceLevelThree("Водоочистители", 5, 0.3, "256");
//        list25x.add(place251);
//        list25x.add(place252);
//        list25x.add(place253);
//        list25x.add(place254);
//        list25x.add(place255);
//        list25x.add(place256);
//
//        place25.setPlaceLevelThreeList(list25x);
//
//        PlaceLevelTwo place26 = new PlaceLevelTwo("Тюрьма", 120, 0.3, "26");
//
//        List<PlaceLevelThree> list26x = new ArrayList<>();
//        PlaceLevelThree place261 = new PlaceLevelThree("КПП", 5, 0.3, "261");
//        PlaceLevelThree place262 = new PlaceLevelThree("Тюремный двор", 5, 0.3, "262");
//        PlaceLevelThree place263 = new PlaceLevelThree("Команата охраны", 5, 0.3, "263");
//        PlaceLevelThree place264 = new PlaceLevelThree("Камеры A", 5, 0.3, "264");
//        PlaceLevelThree place265 = new PlaceLevelThree("Камеры B", 5, 0.3, "265");
//        PlaceLevelThree place266 = new PlaceLevelThree("Кабинет начальника тюрьмы", 5, 0.3, "266");
//        PlaceLevelThree place267 = new PlaceLevelThree("Склад", 5, 0.3, "267");
//        PlaceLevelThree place268 = new PlaceLevelThree("Столовая", 5, 0.3, "268");
//        PlaceLevelThree place269 = new PlaceLevelThree("Кухня", 5, 0.3, "269");
//        list26x.add(place261);
//        list26x.add(place262);
//        list26x.add(place263);
//        list26x.add(place264);
//        list26x.add(place265);
//        list26x.add(place266);
//        list26x.add(place267);
//        list26x.add(place268);
//        list26x.add(place269);
//
//        place26.setPlaceLevelThreeList(list26x);
//
//        List<PlaceLevelTwo> list2xx = new ArrayList<>();
//        list2xx.add(place21);
//        list2xx.add(place22);
//        list2xx.add(place23);
//        list2xx.add(place24);
//        list2xx.add(place25);
//        list2xx.add(place26);
//
//        place2.setPlaceLevelTwoList(list2xx);
//
//        game.getPlaceLevelOneList().add(place2);
//
//        PlaceLevelOne place3 = new PlaceLevelOne("Лес", 1440, 0.5, "3");
//
//        PlaceLevelTwo place31 = new PlaceLevelTwo("Домик лесника", 120, 0.3, "31");
//
//        List<PlaceLevelThree> list31x = new ArrayList<>();
//        PlaceLevelThree place311 = new PlaceLevelThree("Кухня", 5, 0.3, "311");
//        PlaceLevelThree place312 = new PlaceLevelThree("Подвал", 5, 0.3, "312");
//        PlaceLevelThree place313 = new PlaceLevelThree("Спальня", 5, 0.3, "313");
//        list31x.add(place311);
//        list31x.add(place312);
//        list31x.add(place313);
//
//        place31.setPlaceLevelThreeList(list31x);
//
//        PlaceLevelTwo place32 = new PlaceLevelTwo("Заброшенная деревня", 120, 0.3, "32");
//
//        List<PlaceLevelThree> list32x = new ArrayList<>();
//        PlaceLevelThree place321 = new PlaceLevelThree("Колодец", 5, 0.3, "321");
//        PlaceLevelThree place322 = new PlaceLevelThree("Амбар", 5, 0.3, "222");
//        PlaceLevelThree place323 = new PlaceLevelThree("Дома", 5, 0.3, "323");
//        PlaceLevelThree place324 = new PlaceLevelThree("Сад", 5, 0.3, "324");
//        list32x.add(place321);
//        list32x.add(place322);
//        list32x.add(place323);
//        list32x.add(place324);
//
//        place32.setPlaceLevelThreeList(list32x);
//
//        PlaceLevelTwo place33 = new PlaceLevelTwo("Поляна", 120, 0.3, "33");
//
//        List<PlaceLevelTwo> list3xx = new ArrayList<>();
//        list3xx.add(place31);
//        list3xx.add(place32);
//        list3xx.add(place33);
//
//        place3.setPlaceLevelTwoList(list3xx);
//
//        game.getPlaceLevelOneList().add(place3);
//
//        PlaceLevelOne place4 = new PlaceLevelOne("Город X", 1440, 0.5, "4");
//
//        PlaceLevelTwo place41 = new PlaceLevelTwo("Заправка", 120, 0.3, "41");
//
//        List<PlaceLevelThree> list41x = new ArrayList<>();
//        PlaceLevelThree place411 = new PlaceLevelThree("Кафе", 5, 0.3, "411");
//        PlaceLevelThree place412 = new PlaceLevelThree("Бензоколонка", 5, 0.3, "412");
//        PlaceLevelThree place413 = new PlaceLevelThree("Туалет", 5, 0.3, "413");
//        PlaceLevelThree place414 = new PlaceLevelThree("Кухня", 5, 0.3, "414");
//        PlaceLevelThree place415 = new PlaceLevelThree("Кладовка", 5, 0.3, "415");
//        list41x.add(place411);
//        list41x.add(place412);
//        list41x.add(place413);
//        list41x.add(place414);
//        list41x.add(place415);
//
//        place41.setPlaceLevelThreeList(list41x);
//
//        PlaceLevelTwo place42 = new PlaceLevelTwo("Торговый центр", 120, 0.3, "42");
//
//        List<PlaceLevelThree> list42x = new ArrayList<>();
//        PlaceLevelThree place421 = new PlaceLevelThree("Вещеой магазин", 5, 0.3, "421");
//        PlaceLevelThree place422 = new PlaceLevelThree("Ресторан", 5, 0.3, "422");
//        PlaceLevelThree place423 = new PlaceLevelThree("Парковка", 5, 0.3, "423");
//        PlaceLevelThree place424 = new PlaceLevelThree("Магазин кухонной утвари", 5, 0.3, "424");
//        PlaceLevelThree place425 = new PlaceLevelThree("Супермаркет", 5, 0.3, "425");
//        list42x.add(place421);
//        list42x.add(place422);
//        list42x.add(place423);
//        list42x.add(place424);
//        list42x.add(place425);
//
//        place42.setPlaceLevelThreeList(list42x);
//
//        PlaceLevelTwo place43 = new PlaceLevelTwo("Воинская часть", 120, 0.3, "43");
//
//        List<PlaceLevelThree> list43x = new ArrayList<>();
//        PlaceLevelThree place431 = new PlaceLevelThree("КПП", 5, 0.3, "431");
//        PlaceLevelThree place432 = new PlaceLevelThree("Казармы", 5, 0.3, "432");
//        PlaceLevelThree place433 = new PlaceLevelThree("Парковка", 5, 0.3, "433");
//        PlaceLevelThree place434 = new PlaceLevelThree("Склад оружейный", 5, 0.3, "434");
//        PlaceLevelThree place435 = new PlaceLevelThree("Пищевой склад", 5, 0.3, "435");
//        list43x.add(place431);
//        list43x.add(place432);
//        list43x.add(place433);
//        list43x.add(place434);
//        list43x.add(place435);
//
//        place43.setPlaceLevelThreeList(list43x);
//
//        PlaceLevelTwo place44 = new PlaceLevelTwo("Ресторан", 120, 0.3, "44");
//
//        List<PlaceLevelThree> list44x = new ArrayList<>();
//        PlaceLevelThree place441 = new PlaceLevelThree("Кухня", 5, 0.3, "441");
//        PlaceLevelThree place442 = new PlaceLevelThree("Туалет", 5, 0.3, "442");
//        PlaceLevelThree place443 = new PlaceLevelThree("Пищевая кладовка", 5, 0.3, "443");
//        PlaceLevelThree place444 = new PlaceLevelThree("Зал для поситителей", 5, 0.3, "444");
//        list44x.add(place441);
//        list44x.add(place442);
//        list44x.add(place443);
//        list44x.add(place444);
//
//        place44.setPlaceLevelThreeList(list44x);
//
//        PlaceLevelTwo place45 = new PlaceLevelTwo("Порт", 120, 0.3, "45");
//
//        List<PlaceLevelThree> list45x = new ArrayList<>();
//        PlaceLevelThree place451 = new PlaceLevelThree("Потчал A", 5, 0.3, "451");
//        PlaceLevelThree place452 = new PlaceLevelThree("Причал B", 5, 0.3, "452");
//        PlaceLevelThree place453 = new PlaceLevelThree("Причал С", 5, 0.3, "453");
//        PlaceLevelThree place454 = new PlaceLevelThree("Командный центр", 5, 0.3, "454");
//        PlaceLevelThree place455 = new PlaceLevelThree("Склад", 5, 0.3, "455");
//        PlaceLevelThree place456 = new PlaceLevelThree("Верфь", 5, 0.3, "456");
//        PlaceLevelThree place457 = new PlaceLevelThree("Ангар", 5, 0.3, "457");
//        list45x.add(place451);
//        list45x.add(place452);
//        list45x.add(place453);
//        list45x.add(place454);
//        list45x.add(place455);
//        list45x.add(place456);
//        list45x.add(place457);
//
//        place45.setPlaceLevelThreeList(list45x);
//
//        List<PlaceLevelTwo> list4xx = new ArrayList<>();
//        list4xx.add(place41);
//        list4xx.add(place42);
//        list4xx.add(place43);
//        list4xx.add(place44);
//        list4xx.add(place45);
//
//        place4.setPlaceLevelTwoList(list4xx);
//
//        game.getPlaceLevelOneList().add(place4);
//
//        PlaceLevelOne place5 = new PlaceLevelOne("Остров", 1440, 0.5, "5");
//
//        PlaceLevelTwo place51 = new PlaceLevelTwo("Неизвестный дом", 120, 0.3, "51");
//
//        List<PlaceLevelThree> list51x = new ArrayList<>();
//        PlaceLevelThree place511 = new PlaceLevelThree("Гостиная", 5, 0.3, "511");
//        PlaceLevelThree place512 = new PlaceLevelThree("Кухня", 5, 0.3, "512");
//        PlaceLevelThree place513 = new PlaceLevelThree("Подвал", 5, 0.3, "513");
//        PlaceLevelThree place514 = new PlaceLevelThree("Спальня", 5, 0.3, "514");
//        list51x.add(place511);
//        list51x.add(place512);
//        list51x.add(place513);
//        list51x.add(place514);
//
//        place51.setPlaceLevelThreeList(list51x);
//
//        PlaceLevelTwo place52 = new PlaceLevelTwo("Неизвестный склад", 120, 0.3, "52");
//
//        List<PlaceLevelThree> list52x = new ArrayList<>();
//        PlaceLevelThree place521 = new PlaceLevelThree("Кпп", 5, 0.3, "521");
//        PlaceLevelThree place522 = new PlaceLevelThree("Отдел A", 5, 0.3, "522");
//        PlaceLevelThree place523 = new PlaceLevelThree("Отдел B", 5, 0.3, "523");
//        PlaceLevelThree place524 = new PlaceLevelThree("Отдел C", 5, 0.3, "524");
//        list52x.add(place521);
//        list52x.add(place522);
//        list52x.add(place523);
//        list52x.add(place524);
//
//        place52.setPlaceLevelThreeList(list52x);
//
//        PlaceLevelTwo place53 = new PlaceLevelTwo("Лес", 120, 0.3, "53");
//
//        List<PlaceLevelThree> list53x = new ArrayList<>();
//        PlaceLevelThree place531 = new PlaceLevelThree("Сосновая роща", 5, 0.3, "531");
//        PlaceLevelThree place532 = new PlaceLevelThree("Озеро", 5, 0.3, "532");
//        list53x.add(place531);
//        list53x.add(place532);
//
//        place53.setPlaceLevelThreeList(list53x);
//
//        PlaceLevelTwo place54 = new PlaceLevelTwo("Военный бункер", 120, 0.3, "54");
//
//        List<PlaceLevelThree> list54x = new ArrayList<>();
//        PlaceLevelThree place541 = new PlaceLevelThree("КПП", 5, 0.3, "541");
//        PlaceLevelThree place542 = new PlaceLevelThree("Казармы", 5, 0.3, "542");
//        PlaceLevelThree place543 = new PlaceLevelThree("Командный центр", 5, 0.3, "543");
//        PlaceLevelThree place544 = new PlaceLevelThree("Склад", 5, 0.3, "544");
//        PlaceLevelThree place545 = new PlaceLevelThree("Подземный причал", 5, 0.3, "545");
//        PlaceLevelThree place546 = new PlaceLevelThree("Склад", 5, 0.3, "546");
//        list54x.add(place541);
//        list54x.add(place542);
//        list54x.add(place543);
//        list54x.add(place544);
//        list54x.add(place545);
//        list54x.add(place546);
//
//        place54.setPlaceLevelThreeList(list54x);
//
//        List<PlaceLevelTwo> list5xx = new ArrayList<>();
//        list5xx.add(place51);
//        list5xx.add(place52);
//        list5xx.add(place53);
//        list5xx.add(place54);
//
//        place5.setPlaceLevelTwoList(list5xx);
//
//        game.getPlaceLevelOneList().add(place5);

        Map<Integer, Location> locationMap = new HashMap<>();
        locationMap.put(location1.getId(), location1);

        Map<Integer, Place> placeMap = new HashMap<>();
        placeMap.put(place11.getId(), place11);
        placeMap.put(place12.getId(), place12);
        placeMap.put(place13.getId(), place13);
        placeMap.put(place14.getId(), place14);
        placeMap.put(place15.getId(), place15);
        placeMap.put(place16.getId(), place16);
        List<Way> wayList = new LinkedList<>();

        GameData gameData = new GameData();
        gameData.setAllMinutes(720);
        gameData.setLastDay(100);
        gameData.setCurrentPlace(place11, true);
        gameData.setCurrentLocation(location1, true);

        MapInterface mapInterface = new MapInterface(gameData, locationMap, placeMap, wayList);
        gameInterface.setMapInterface(mapInterface);
    }

    private static void addNewPlayer(GameInterface gameInterface, String name){
        Player player = new Player(name);
        player.setName(name);
        player.setEnergy(80);
        player.setHealth(80);
        player.setSatiety(80);
        player.setThirst(80);

        Bag bag = new Bag("Простой рюкзак", BAG_3_IMG, 5);
        bag.addOwner(player, null);
        sResourceMap.put(bag.getId(), bag);

        BodyClothes bathrobe = new BodyClothes("Кожанкаа", 1, BODY_CLOTHES_1_IMG);
        bathrobe.addOwner(player, null);
        sResourceMap.put(bathrobe.getId(), bathrobe);

        LegsClothes pajamas = new LegsClothes("Пижама", 1, LEGS_CLOTHES_1_IMG);
        pajamas.addOwner(player, null);
        sResourceMap.put(pajamas.getId(), pajamas);

        FeetClothes slippers = new FeetClothes("Кроссовки", 1, FEET_CLOTHES_1_IMG);
        slippers.addOwner(player, null);
        sResourceMap.put(slippers.getId(), slippers);

        HeadClothes hat = new HeadClothes("Кепка", 1, HEAD_CLOTHES_1_IMG);
        hat.addOwner(player, null);
        sResourceMap.put(hat.getId(), hat);

        FireArms fireArms = new FireArms("Автомат", 5, FIRE_ARMS_3_IMG, FireArms.Type.MACHINE);
        Cartridges cartridges = new Cartridges("Пули", CARTRIDGES_3_IMG, 100, FireArms.Type.MACHINE);
        fireArms.setCartridges(cartridges, true);

        fireArms.addOwner(player, Player.FIRST_SLOT);
        sResourceMap.put(fireArms.getId(), fireArms);
        sResourceMap.put(cartridges.getId(), cartridges);

        SteelArms steelArms = new SteelArms("Топор", 2, STEEL_ARMS_2_IMG);
        steelArms.addOwner(player, Player.SECOND_SLOT);
        sResourceMap.put(steelArms.getId(), steelArms);

        Bag bagTransport = new Bag("Багажник", AssertResourceName.BAG_TRANSPORT_IMG, 20);

        Transport transport = new Transport("Военный джип", 0.05, 20, 1000, TRANSPORT_1_IMG);
        transport.setBag(bagTransport, true);
        transport.addOwner(player, null);
        sResourceMap.put(bagTransport.getId(), bagTransport);
        sResourceMap.put(transport.getId(), transport);

        Food food = new Food("Консервы", 100, FOOD_1_IMG);
        Liquid liquid = new Liquid("Бутылка воды", 100, LIQUID_3_IMG);

        food.addOwner(bag, null);
        liquid.addOwner(bag, null);

        sResourceMap.put(food.getId(), food);
        sResourceMap.put(liquid.getId(), liquid);

        Log.i("TAG", "Bag size - " + bag.getResourceList().size());

        PlayerInterface playerInterface = new PlayerInterface(player);
        gameInterface.setPlayerInterface(playerInterface);

        ResourceInterface resourceInterface = new ResourceInterface(sResourceMap);
        gameInterface.setResourceInterface(resourceInterface);

        sResourceMap = null;
    }

    private static void addNewResourceList(GameInterface gameInterface){
        //Создание массива с ресурсами

        //Создание локаций
        //Создание мест
        //Создание конечных мест

        //Создание и распределение Event по Location



        //Создание и распределение Resource по PlaceLevelThree

        //Добавление воды
        for (int i = 0; i < 10; i++) {
            Liquid liquid = new Liquid("Бутылка воды", 10, LIQUID_1_IMG);
            sResourceMap.put(liquid.getId(), liquid);
        }

        // Добавление еды
        for (int i = 0; i < 4; i++){
            Food food = new Food("Батончик", 10, FOOD_1_IMG);
            sResourceMap.put(food.getId(), food);
            if (i < 1) {
                food = new Food("Оленина", 70, FOOD_1_IMG);
                sResourceMap.put(food.getId(), food);
            }
            if (i < 1){
                food = new Food("Мясо кролика", 50, FOOD_2_IMG);
                sResourceMap.put(food.getId(), food);
            }
            if (i < 1){
                food = new Food("Мясо белки", 45, FOOD_2_IMG);
                sResourceMap.put(food.getId(), food);
            }
            if (i < 3){
                food = new Food("Картошка", 20, FOOD_1_IMG);
                sResourceMap.put(food.getId(), food);
            }
            if (i < 4){
                food = new Food("Сухари", 15, FOOD_1_IMG);
                sResourceMap.put(food.getId(), food);
            }
        }

        // добавление лекарств
        for (int i = 0; i < 5; i++) {
            Drug drug = new Drug("Бинты", 10, DRUG_1_IMG);
            sResourceMap.put(drug.getId(), drug);
            if (i < 2) {
                drug = new Drug("Аптечка", 50, DRUG_2_IMG);
                sResourceMap.put(drug.getId(), drug);
            }
            if (i < 1) {
                drug = new Drug("Антибиотики", 30, DRUG_3_IMG);
                sResourceMap.put(drug.getId(), drug);
            }
        }

        // добавление транспорта
        Bag bagTransport1 = new Bag("Багажник", AssertResourceName.BAG_TRANSPORT_IMG, 20);
        sResourceMap.put(bagTransport1.getId(), bagTransport1);
        Bag bagTransport2 = new Bag("Багажник", AssertResourceName.BAG_TRANSPORT_IMG, 15);
        sResourceMap.put(bagTransport2.getId(), bagTransport2);
        Bag bagTransport4 = new Bag("Багажник", AssertResourceName.BAG_TRANSPORT_IMG, 5);
        sResourceMap.put(bagTransport2.getId(), bagTransport2);

        Transport transport1 = new Transport("Военный джип", 0.05, 20, 1000, TRANSPORT_1_IMG);
        transport1.setBag(bagTransport1, true);
        sResourceMap.put(transport1.getId(), transport1);

        Transport transport2 = new Transport("Легковой автомобиль", 0.1, 10, 1000, TRANSPORT_1_IMG);
        transport2.setBag(bagTransport2, true);
        sResourceMap.put(transport2.getId(), transport2);

        Transport transport4 = new Transport("Мопед", 0.3, 1, 1000, TRANSPORT_1_IMG);
        transport4.setBag(bagTransport4, true);
        sResourceMap.put(transport4.getId(), transport4);

        // добавление оружия
        for (int i = 0; i < 1/*4*/; i++) {
            if (i < 1){
                FireArms fireArms = new FireArms("Автомат", 1, FIRE_ARMS_3_IMG, FireArms.Type.MACHINE_GUN);
                Cartridges cartridges = new Cartridges("Пули", CARTRIDGES_3_IMG, 50, FireArms.Type.MACHINE_GUN);
                fireArms.setCartridges(cartridges, true);
                sResourceMap.put(fireArms.getId(), fireArms);
                sResourceMap.put(cartridges.getId(), cartridges);
                SteelArms steelArms = new SteelArms("Катана", 5, STEEL_ARMS_3_IMG);
                sResourceMap.put(steelArms.getId(), steelArms);
                System.out.print(fireArms.getCartridges());
            }
            if (i < 2){
                FireArms fireArms = new FireArms("Пистолет-пулемет", 2, FIRE_ARMS_2_IMG, FireArms.Type.MACHINE);
                Cartridges cartridges = new Cartridges("Пули", FIRE_ARMS_2_IMG, 50, FireArms.Type.MACHINE);
                fireArms.setCartridges(cartridges, true);
                sResourceMap.put(fireArms.getId(), fireArms);
                sResourceMap.put(cartridges.getId(), cartridges);
                SteelArms steelArms = new SteelArms("Топор", 7, STEEL_ARMS_2_IMG);
                sResourceMap.put(steelArms.getId(), steelArms);
                System.out.print(fireArms.getCartridges());
            }
            if (i < 3){
                FireArms fireArms = new FireArms("Пистолет", 3, FIRE_ARMS_1_IMG, FireArms.Type.PISTOL);
                Cartridges cartridges = new Cartridges("Пули", FIRE_ARMS_1_IMG, 50, FireArms.Type.PISTOL);
                fireArms.setCartridges(cartridges, true);
                sResourceMap.put(fireArms.getId(), fireArms);
                sResourceMap.put(cartridges.getId(), cartridges);
                SteelArms steelArms = new SteelArms("Нож", 8, STEEL_ARMS_1_IMG);
                sResourceMap.put(steelArms.getId(), steelArms);
            }
        }

        // добавление пуль

        for (int i = 0; i < 3; i ++) {
            Cartridges pistol = new Cartridges("Пули для пистолета", CARTRIDGES_1_IMG, 25, FireArms.Type.PISTOL);
            Cartridges machine = new Cartridges("Пули для пистолета-пулемета", CARTRIDGES_2_IMG, 50, FireArms.Type.MACHINE);
            Cartridges machineGun = new Cartridges("Пули для автомата", CARTRIDGES_3_IMG, 100, FireArms.Type.MACHINE_GUN);
            sResourceMap.put(pistol.getId(), pistol);
            sResourceMap.put(machine.getId(), machine);
            sResourceMap.put(machineGun.getId(), machineGun);
        }

        // добавление одежды

        // обувь

        for (int i = 0; i < 3; i++) {
            FeetClothes sneakers = new FeetClothes("Кроссовки", 2, FEET_CLOTHES_1_IMG);
            sResourceMap.put(sneakers.getId(), sneakers);
            if (i < 2){
                FeetClothes shoes = new FeetClothes("Ботинки", 5, FEET_CLOTHES_2_IMG);
                sResourceMap.put(shoes.getId(), shoes);
            }
            if (i < 1){
                FeetClothes ankleBoots = new FeetClothes("Берцы", 8, FEET_CLOTHES_3_IMG);
                sResourceMap.put(ankleBoots.getId(), ankleBoots);
            }
        }

        // тело

        for (int i = 0; i < 3; i++) {
            BodyClothes shirt = new BodyClothes("Кожанка", 2, BODY_CLOTHES_1_IMG);
            sResourceMap.put(shirt.getId(), shirt);
            if (i < 2){
                BodyClothes sweatshirt = new BodyClothes("Бронижелет гражданский", 5, BODY_CLOTHES_2_IMG);
                sResourceMap.put(sweatshirt.getId(), sweatshirt);
            }
            if (i < 1){
                BodyClothes jacket = new BodyClothes("Бронижелет военный", 8, BODY_CLOTHES_3_IMG);
                sResourceMap.put(jacket.getId(), jacket);
            }
        }

        // ноги

        for (int i = 0; i < 3; i++) {
            LegsClothes shorts = new LegsClothes("Шорты", 2, LEGS_CLOTHES_1_IMG);
            sResourceMap.put(shorts.getId(), shorts);
            if (i < 2){
                LegsClothes pants = new LegsClothes("Брюки", 5, LEGS_CLOTHES_2_IMG);
                sResourceMap.put(pants.getId(), pants);
            }
            if (i < 1){
                LegsClothes camuflagePants = new LegsClothes("Камуфлированные штаны", 8, LEGS_CLOTHES_3_IMG);
                sResourceMap.put(camuflagePants.getId(), camuflagePants);
            }
        }

        // голова

        for (int i = 0; i < 3; i++) {
            HeadClothes cap = new HeadClothes("Бандама", 2, HEAD_CLOTHES_1_IMG);
            sResourceMap.put(cap.getId(), cap);
            if (i < 2){
                HeadClothes hat = new HeadClothes("Шапка", 5, HEAD_CLOTHES_2_IMG);
                sResourceMap.put(hat.getId(), hat);
            }
            if (i < 1){
                HeadClothes helmet = new HeadClothes("Каска", 8, HEAD_CLOTHES_3_IMG);
                sResourceMap.put(helmet.getId(), helmet);
            }
        }

        List<Place> placeList = new ArrayList<>(gameInterface.getPlaceList());

        for (Resource resource : sResourceMap.values()){
            int placeRandom = (int)((Math.random()*(gameInterface.getPlaceList().size())));
            Place place = placeList.get(placeRandom);
            resource.addOwner(place, null);
        }

        Bag bagTransportTest = new Bag("Багажник", AssertResourceName.BAG_TRANSPORT_IMG, 2);
        sResourceMap.put(bagTransportTest.getId(), bagTransportTest);

        Transport transportTest = new Transport("Военный джип", 0.05, 20, 1000, TRANSPORT_1_IMG);
        transportTest.setBag(bagTransportTest, true);
        sResourceMap.put(transportTest.getId(), transportTest);

        Bag bagTest = new Bag("Мал сумка", AssertResourceName.BAG_1_IMG, 3);
        sResourceMap.put(bagTest.getId(), bagTest);

        Place place = gameInterface.getCurrentPlace();

        transportTest.addOwner(place, null);
        bagTest.addOwner(place, null);
    }

    static void continueGame(GameInterface gameInterface){
        addCurrentMap(gameInterface);
        addCurrentPlayer(gameInterface);
        addCurrentResourceList(gameInterface);
    }

    private static void addCurrentPlayer(GameInterface gameInterface){
        PlayerInterface playerInterface = new PlayerInterface(BDHelper.getPlayer());
        gameInterface.setPlayerInterface(playerInterface);
    }

    private static void addCurrentMap(GameInterface gameInterface){
        Map<Integer, Location> locationMap = BDHelper.getLocationList();
        Map<Integer, Place> placeMap = BDHelper.getPlaceList();
        List<Way> wayList = BDHelper.getWayList();
        GameData gameData = BDHelper.getGameData();

        for (Location location : locationMap.values()){
            for (Integer id : location.getPlaceIdList()){
                location.addPlace(placeMap.get(id), false);
            }
        }

        gameData.setCurrentLocation(locationMap.get(gameData.getCurrentLocationId()), false);
        gameData.setCurrentPlace(placeMap.get(gameData.getCurrentPlaceId()), false);

        MapInterface mapInterface = new MapInterface(gameData, locationMap, placeMap, wayList);
        gameInterface.setMapInterface(mapInterface);
    }

    private static void addCurrentResourceList(GameInterface gameInterface){
        Map<UUID, Resource> resourceMap = BDHelper.getResourceMap();
        Log.i("TAG", "Size in the last continue = " + resourceMap.size());

        for (Resource owner : resourceMap.values()){
            if (owner instanceof Bag){
                for (UUID uuid : ((Bag)owner).getResourceUUIDList()){
                    resourceMap.get(uuid).addOwner((Bag)owner, null);
                }
                Log.i("TAG", "B R SIZE - " + ((Bag)owner).getResourceList().size() + "/" + ((Bag)owner).getResourceUUIDList().size());
            } else if (owner instanceof Transport) {
                Bag bag = (Bag) resourceMap.get(((Transport)owner).getBagUUID());
                ((Transport)owner).setBag(bag, false);
            } else if (owner instanceof FireArms) {
                Cartridges cartridges = (Cartridges) resourceMap.get(((FireArms)owner).getCartridgesUUID());
                ((FireArms)owner).setCartridges(cartridges, false);
            }
        }

        Player player = gameInterface.getPlayer();

        Bag bag = ((Bag)resourceMap.get(player.getCurrentBagUUID()));
        if (bag != null){
            bag.addOwner(player, null);
        }
        Transport transport = ((Transport)resourceMap.get(player.getCurrentTransportUUID()));
        if (transport != null){
            transport.addOwner(player, null);
        }
        Weapon weapon1 = ((Weapon)resourceMap.get(player.getCurrentFirstWeaponUUID()));
        if (weapon1 != null){
            weapon1.addOwner(player, Player.FIRST_SLOT);
        }
        Weapon weapon2 = ((Weapon)resourceMap.get(player.getCurrentSecondWeaponUUID()));
        if (weapon2 != null){
            weapon2.addOwner(player, Player.SECOND_SLOT);
        }
        HeadClothes headClothes = ((HeadClothes)resourceMap.get(player.getCurrentHeadClothesUUID()));
        if (headClothes != null){
            headClothes.addOwner(player, null);
        }
        BodyClothes bodyClothes = ((BodyClothes)resourceMap.get(player.getCurrentBodyClothesUUID()));
        if (bodyClothes != null){
            bodyClothes.addOwner(player, null);
        }
        LegsClothes legsClothes = ((LegsClothes)resourceMap.get(player.getCurrentLegsClothesUUID()));
        if (legsClothes != null){
            legsClothes.addOwner(player, null);
        }
        FeetClothes feetClothes = ((FeetClothes)resourceMap.get(player.getCurrentFeetClothesUUID()));
        if (feetClothes != null){
            feetClothes.addOwner(player, null);
        }

        for (Place place : gameInterface.getPlaceList()){
            for (UUID uuid : place.getResourceUUIDList()){
                resourceMap.get(uuid).addOwner(place, null);
            }
            Log.i("TAG", "P R SIZE - " + place.getResourceList().size() + "/" + place.getResourceUUIDList().size());
        }

        ResourceInterface resourceInterface = new ResourceInterface(resourceMap);
        gameInterface.setResourceInterface(resourceInterface);


    }

}
