package com.reclaite.parser;

import jakarta.annotation.PostConstruct;
import com.reclaite.model.*;
import com.reclaite.parser.Parser;
import com.reclaite.parser.ParserType;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.boot.json.GsonJsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EldoradoParser extends Parser {
    
    public EldoradoParser(ParserType parserType) {
        super(parserType);
    }
    
    @Override
    public void processParser() {
        registerParser(ProductType.CPU, (json) -> {
            CPU cpu = new CPU();
            if (json == null) {
                return null;
            }
            
            Map<String, String> attributes = (Map<String, String>) ((List<Map<String, Object>>) json.get("listingDescription"))
                .get(0)
                .get("attributeNameToValueMap");
            
            cpu.setTitle(((String) json.get("name")).replace("Процессор ", ""));
            cpu.setCores(Integer.parseInt(attributes.get("Количество ядер")));
            cpu.setThreads(cpu.getCores() * 2);
            cpu.setSocket(attributes.get("Сокет").toUpperCase());
            
            List<Map<String, Object>> images = (List<Map<String, Object>>) json.get("images");
            if (images.size() > 0) {
                cpu.setImages(images.stream().map(img -> "https://static.eldorado.ru" + img.get("url")).collect(Collectors.toList()));
            }
            cpu.setPrice(((double) json.get("price")));
            cpu.setOtherSpecifications(
                attributes
            );
            return cpu;
        });
        
        registerParser(ProductType.COOLER, (json) -> {
            Cooler cooler = new Cooler();
            if (json == null) {
                return null;
            }
            
            Map<String, String> attributes = (Map<String, String>) ((List<Map<String, Object>>) json.get("listingDescription"))
                .get(0)
                .get("attributeNameToValueMap");
            
            cooler.setTitle(((String) json.get("name")).replace("Кулер для процессора ", ""));
            cooler.setSocket(Arrays.asList(attributes.get("Socket").toUpperCase().split(", ")));
            
            List<Map<String, Object>> images = (List<Map<String, Object>>) json.get("images");
            if (images.size() > 0) {
                cooler.setImages(images.stream().map(img -> "https://static.eldorado.ru" + img.get("url")).collect(Collectors.toList()));
            }
            cooler.setPrice(((double) json.get("price")));
            cooler.setOtherSpecifications(
                attributes
            );
            return cooler;
        });
        
        registerParser(ProductType.MOTHERBOARD, (json) -> {
            Motherboard motherboard = new Motherboard();
            if (json == null) {
                return null;
            }
            
            Map<String, String> attributes = ((List<Map<String, Object>>) json.get("listingDescription"))
                .stream()
                .map(map -> ((Map<String, String>) map.get("attributeNameToValueMap")))
                .toList()
                .stream()
                .reduce(new HashMap<>(), (map1, map2) -> {
                    map1.putAll(map2);
                    return map1;
                });
            
            motherboard.setTitle(((String) json.get("name")).replace("Материнская плата ", ""));
            motherboard.setSocket(attributes.get("Socket").toUpperCase());
            
            List<Map<String, Object>> images = (List<Map<String, Object>>) json.get("images");
            if (images.size() > 0) {
                motherboard.setImages(images.stream().map(img -> "https://static.eldorado.ru" + img.get("url")).collect(Collectors.toList()));
            }
            motherboard.setPrice(((double) json.get("price")));
            motherboard.setOtherSpecifications(
                attributes
            );
            return motherboard;
        });
    
        registerParser(ProductType.VIDEOCARD, (json) -> {
            VideoCard motherboard = new VideoCard();
            if (json == null) {
                return null;
            }
        
            Map<String, String> attributes = ((List<Map<String, Object>>) json.get("listingDescription"))
                .stream()
                .map(map -> ((Map<String, String>) map.get("attributeNameToValueMap")))
                .toList()
                .stream()
                .reduce(new HashMap<>(), (map1, map2) -> {
                    map1.putAll(map2);
                    return map1;
                });
        
            motherboard.setTitle(((String) json.get("name")).replace("Видеокарта ", ""));
        
            List<Map<String, Object>> images = (List<Map<String, Object>>) json.get("images");
            if (images.size() > 0) {
                motherboard.setImages(images.stream().map(img -> "https://static.eldorado.ru" + img.get("url")).collect(Collectors.toList()));
            }
            motherboard.setPrice(((double) json.get("price")));
            motherboard.setOtherSpecifications(
                attributes
            );
            return motherboard;
        });
    
        registerParser(ProductType.MEMORY, (json) -> {
            Memory motherboard = new Memory();
            if (json == null) {
                return null;
            }
        
            Map<String, String> attributes = ((List<Map<String, Object>>) json.get("listingDescription"))
                .stream()
                .map(map -> ((Map<String, String>) map.get("attributeNameToValueMap")))
                .toList()
                .stream()
                .reduce(new HashMap<>(), (map1, map2) -> {
                    map1.putAll(map2);
                    return map1;
                });
        
            motherboard.setTitle(((String) json.get("name")).replace("Оперативная память ", ""));
        
            List<Map<String, Object>> images = (List<Map<String, Object>>) json.get("images");
            if (images.size() > 0) {
                motherboard.setImages(images.stream().map(img -> "https://static.eldorado.ru" + img.get("url")).collect(Collectors.toList()));
            }
            motherboard.setPrice(((double) json.get("price")));
            motherboard.setOtherSpecifications(
                attributes
            );
            return motherboard;
        });
    
        registerParser(ProductType.STORAGE, (json) -> {
            Storage motherboard = new Storage();
            if (json == null) {
                return null;
            }
        
            Map<String, String> attributes = ((List<Map<String, Object>>) json.get("listingDescription"))
                .stream()
                .map(map -> ((Map<String, String>) map.get("attributeNameToValueMap")))
                .toList()
                .stream()
                .reduce(new HashMap<>(), (map1, map2) -> {
                    map1.putAll(map2);
                    return map1;
                });
        
            motherboard.setTitle(((String) json.get("name")).replace("SSD накопитель ", ""));
        
            List<Map<String, Object>> images = (List<Map<String, Object>>) json.get("images");
            if (images.size() > 0) {
                motherboard.setImages(images.stream().map(img -> "https://static.eldorado.ru" + img.get("url")).collect(Collectors.toList()));
            }
            motherboard.setPrice(((double) json.get("price")));
            motherboard.setOtherSpecifications(
                attributes
            );
            return motherboard;
        });
    
        registerParser(ProductType.POWERSUPPLY, (json) -> {
            PowerSupply motherboard = new PowerSupply();
            if (json == null) {
                return null;
            }
        
            Map<String, String> attributes = ((List<Map<String, Object>>) json.get("listingDescription"))
                .stream()
                .map(map -> ((Map<String, String>) map.get("attributeNameToValueMap")))
                .toList()
                .stream()
                .reduce(new HashMap<>(), (map1, map2) -> {
                    map1.putAll(map2);
                    return map1;
                });
        
            motherboard.setTitle(((String) json.get("name")).replace("SSD накопитель ", ""));
        
            List<Map<String, Object>> images = (List<Map<String, Object>>) json.get("images");
            if (images.size() > 0) {
                motherboard.setImages(images.stream().map(img -> "https://static.eldorado.ru" + img.get("url")).collect(Collectors.toList()));
            }
            motherboard.setPrice(((double) json.get("price")));
            motherboard.setOtherSpecifications(
                attributes
            );
            return motherboard;
        });
    
        registerParser(ProductType.COMPUTERCASE, (json) -> {
            ComputerCase motherboard = new ComputerCase();
            if (json == null) {
                return null;
            }
        
            Map<String, String> attributes = ((List<Map<String, Object>>) json.get("listingDescription"))
                .stream()
                .map(map -> ((Map<String, String>) map.get("attributeNameToValueMap")))
                .toList()
                .stream()
                .reduce(new HashMap<>(), (map1, map2) -> {
                    map1.putAll(map2);
                    return map1;
                });
        
            motherboard.setTitle(((String) json.get("name")).replace("Корпус для компьютера ", ""));
        
            List<Map<String, Object>> images = (List<Map<String, Object>>) json.get("images");
            if (images.size() > 0) {
                motherboard.setImages(images.stream().map(img -> "https://static.eldorado.ru" + img.get("url")).collect(Collectors.toList()));
            }
            motherboard.setPrice(((double) json.get("price")));
            motherboard.setOtherSpecifications(
                attributes
            );
            return motherboard;
        });
    }
    
    @Override
    public Map<String, Object> getMatchedProduct(ProductType productType, String productName) {
        try {
            Connection.Response response = Jsoup.connect(
                    "https://www.eldorado.ru/sem/v3/a711/products?" +
                        "rootRestrictedCategoryId=0&" +
                        "query=" + productName + "&" +
                        "orderField=popular&" +
                        "limit=36&" +
                        "regionId=15591&" +
                        "strategy=sold_amount_relevance&" +
                        "soldAmountBoost=0.5")
                .header("authority", "www.eldorado.ru")
                .header("accept", "application/json, text/plain, */*")
                .header("accept-language", "ru,en;q=0.9")
                .header("authorization", "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjp7IlNJRCI6Im5tbDQ4Y2sydnZmc3BvMzZkdGpuaTc2bTI4In0sImV4cCI6MTY4NzI0NjcwNn0.L56NrP5-mH90-7XTl7Cp8P3pnmfXPgG1NoCJPw9nCynyrRgIjU0GbDYyR9xYNUszJFeHLgpJeYmtbAYtG1IiYQ")
                .cookie("ab_user", "8056823240100")
                .cookie("ab_segment", "80")
                .cookie("ABT_test", "D")
                .cookie("ek_ab_test", "B")
                .cookie("AUTORIZZ", "0")
                .cookie("AC", "1")
                .cookie("lv_user_org", "0")
                .cookie("el_group_user_org", "0")
                .cookie("bonus_cobrand_showed", "0")
                .cookie("_gcl_au", "1.1.896864992.1686861914")
                .cookie("flocktory-uuid", "db003fa1-b0a5-470f-9231-c9d31896e6bf-2")
                .cookie("advcake_trackid", "8e33d9fc-d4e6-87bc-b5bf-b2c68cd547eb")
                .cookie("advcake_session_id", "99b4b06e-51e1-c59b-beae-146fad99c0cd")
                .cookie("_slid", "648b785a8c2e4b118005987c")
                .cookie("tmr_lvid", "a1819823ec782abd986fb705c86aa5e8")
                .cookie("tmr_lvidTS", "1686861914918")
                .cookie("_ym_uid", "1686861915637531540")
                .cookie("_ym_d", "1686861915")
                .cookie("_userGUID", "0:lixm15c1:s_KQVzQEhBxipkikTlp_77wCLEQ24WPE")
                .cookie("uxs_uid", "89586460-0bbd-11ee-b844-676fd8747827")
                .cookie("iRegionSectionId", "11300")
                .cookie("grs", "15591")
                .cookie("_slid_server", "648b785a8c2e4b118005987c")
                .cookie("mindboxDeviceUUID", "8e2cfe27-7131-4a60-9442-4ef8c67423ab")
                .cookie("directCrm-session", "%7B%22deviceGuid%22%3A%228e2cfe27-7131-4a60-9442-4ef8c67423ab%22%7D")
                .cookie("dt", "1")
                .cookie("PHPSESSID", "nml48ck2vvfspo36dtjni76m28")
                .cookie("BITRIX_SM_SALE_UID", "31796754975")
                .cookie("BITRIX_SM_SALE_UID_CS", "38728a94e5a737e0ce7a67bceb5f6911")
                .cookie("_slsession", "6B798633-44C7-4FB3-B713-6608DC1C2F0B")
                .cookie("_slfreq", "646f4a3ad9b723086101fbe5%3A646f4a3ad9b723086101fbe9%3A1686869115%3B646f4a3ad9b723086101fbec%3A646f4a3ad9b723086101fbf0%3A1687252106")
                .cookie("_sp_ses.3135", "*")
                .cookie("_gid", "GA1.2.1791474861.1687244906")
                .cookie("_ym_isad", "1")
                .cookie("_ym_visorc", "w")
                .cookie("dSesn", "b23eefa7-a3f2-5417-040d-577b48418548")
                .cookie("_dvs", "0:lj3y1y6z:oSwFoIce3XHRFc89N9jSTgT8kM9ePcXz")
                .cookie("cookAllow", "1")
                .cookie("_dc_gtm_UA-44012634-4", "1")
                .cookie("gssc157", "")
                .cookie("USER_AUTH_GTM", "0")
                .cookie("bCNT", "0%3A0")
                .cookie("BITRIX_SM_ELD_TZ_OFFSET", "-180")
                .cookie("_ga", "GA1.1.349864307.1686861914")
                .cookie("digi-SearchVisitor", "10%3A4")
                .cookie("rrpvid", "503132481652673")
                .cookie("digi_uc", "W1sidiIsIjU5MDA2Nzc5MSIsMTY4NzI0NTA5MjQwNV1d")
                .cookie("tmr_detect", "1%7C1687245092408")
                .cookie("rcuid", "649151269144efaeaff292f1")
                .cookie("unauthorizedId", "ns3qf1pqes4pz5z3q8ln0b4d8nznrjlz5zkvr5kl35zufezsjenrgzvlcb0kq0zmhdcux43zt216jp1bbg8z0vt3br0owi3gjy97h5emhnp63t2animh5me2v1ney2ahk11o4879zd0wthc8tvfc35nvrdn4pu1cs8bf8to3k7o1pdd73f3dqqvibwbpb8zqkzz1exz3kg0ctgfmo8hmtpf3pm59e9ms79b16i1fbnd5y3a041vsaoudkpyydy7s")
                .cookie("cfidsgib-w-eldorado", "DkqX+yUWqL5AiH1yqkaWalASWqo0ekkRO0ctcPEAI7THXB23t8xU+2c4G34FUu6uoGz+p7oWw6DzY/ZtQjE1HdNZq6OCFi7wCCIarJ2/l93xiWfUCmmIvCVyVgwvSrO2cKzwHBeTjRDDfvTAo1uyO39+G8pk6rbhgGIt2w")
                .cookie("gsscgib-w-eldorado", "L3AA3Tk9d1AYTe630kMbrjNSOV9mQQ+Ad4Nl75Rdw+Kbu25b2sCJ9VUYkPmDvmFLp4d86os2OZnFkL5q89XMrDYrYrVt1yALu4DsAZmuKyfx5fhwsMuf/Ws8j0bbDJC7kNzoKHf4Mrkop40YJz+np3gH6gLouU0j+SqJgcXW5lx1UFlz9aGyjcQ20yo9JX4zCRUUUr/4dcbZlCAuBx3gugDPYEAyAxh/Gaq1Ola63wZO/lHq0q2RBDa+s47TRw")
                .cookie("gsscgib-w-eldorado", "L3AA3Tk9d1AYTe630kMbrjNSOV9mQQ+Ad4Nl75Rdw+Kbu25b2sCJ9VUYkPmDvmFLp4d86os2OZnFkL5q89XMrDYrYrVt1yALu4DsAZmuKyfx5fhwsMuf/Ws8j0bbDJC7kNzoKHf4Mrkop40YJz+np3gH6gLouU0j+SqJgcXW5lx1UFlz9aGyjcQ20yo9JX4zCRUUUr/4dcbZlCAuBx3gugDPYEAyAxh/Gaq1Ola63wZO/lHq0q2RBDa+s47TRw")
                .cookie("_sp_id.3135", "5f7e23b1-d2a5-40bb-83d2-59aaeb87e49b.1686861915.3.1687245103.1686866368.4657ff6b-133f-43f7-8906-64ee61e977e8.a4db3a56-07b1-4f00-9bc6-01cbfa635600.5d534cce-2efd-4e21-8fd7-bd69c9aa0948.1687244906175.44")
                .cookie("_ga_4P3TZK55KZ", "GS1.1.1687244906.3.1.1687245103.0.0.0")
                .cookie("fgsscgib-w-eldorado", "eEbO100ddc37a80702901a42bfb19d6db55ad8ff")
                .cookie("fgsscgib-w-eldorado", "gRCY86fa91497bc9124d1dfe552d7b6b730e8a93")
                .header("referer", "https://www.eldorado.ru/d/kompyuternye-komplektuyushchie/")
                .header("sec-ch-ua", "\"Chromium\";v=\"112\", \"YaBrowser\";v=\"23\", \"Not:A-Brand\";v=\"99\"")
                .header("sec-ch-ua-mobile", "?0")
                .header("sec-ch-ua-platform", "\"Windows\"")
                .header("sec-fetch-dest", "empty")
                .header("sec-fetch-mode", "cors")
                .header("sec-fetch-site", "same-origin")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 YaBrowser/23.5.2.625 Yowser/2.5 Safari/537.36")
                .header("x-gib-fgsscgib-w-eldorado", "eEbO100ddc37a80702901a42bfb19d6db55ad8ff")
                .header("x-gib-gsscgib-w-eldorado", "L3AA3Tk9d1AYTe630kMbrjNSOV9mQQ+Ad4Nl75Rdw+Kbu25b2sCJ9VUYkPmDvmFLp4d86os2OZnFkL5q89XMrDYrYrVt1yALu4DsAZmuKyfx5fhwsMuf/Ws8j0bbDJC7kNzoKHf4Mrkop40YJz+np3gH6gLouU0j+SqJgcXW5lx1UFlz9aGyjcQ20yo9JX4zCRUUUr/4dcbZlCAuBx3gugDPYEAyAxh/Gaq1Ola63wZO/lHq0q2RBDa+s47TRw==")
                .method(org.jsoup.Connection.Method.GET)
                .ignoreContentType(true)
                .execute();
            
            Map<String, Object> map = new GsonJsonParser().parseMap(response.body());
            List<Map<String, Object>> matchedProducts = ((ArrayList<Map<String, Object>>) map.get("data"))
                .stream()
                .filter(productInfo -> ((String) productInfo.get("name")).toLowerCase().contains(productName.toLowerCase()))
                .toList();
            return matchedProducts.size() > 0 ? matchedProducts.get(0) : null;
        } catch (IOException exception) {
            return null;
        }
    }
    
}
