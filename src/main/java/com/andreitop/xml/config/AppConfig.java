package com.andreitop.xml.config;

import com.andreitop.xml.mount.Mount;
import com.andreitop.xml.mount.Tiger;
import com.andreitop.xml.mount.Wolf;
import com.andreitop.xml.unit.Human;
import com.andreitop.xml.unit.Orc;
import com.andreitop.xml.unit.Troll;
import com.andreitop.xml.unit.Unit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Configuration
@PropertySource("config/heroes.properties")
public class AppConfig {

    @Bean
    public Mount frostWolf() {
        return new Wolf();
    }

    @Bean
    public Mount shadowTiger() {
        return new Tiger();
    }

    @Bean
    public String furryAxe() {
        return "furryAxe";
    }

    @Bean
    public String thunderFury() {
        return "thunderFury";
    }

    @Bean
    public String soulBlade() {
        return "soulBlade";
    }

    @Bean
    public Unit knight() {
        return new Human(shadowTiger(), thunderFury(), soulBlade());
    }

    @Bean
    public Unit trall() {
        Orc trall = new Orc(frostWolf());
        trall.setWeapon(furryAxe());
        trall.setColorCode(9);
        return trall;
    }

    @Bean
    public DateFormat dateFormatter() {
        return new SimpleDateFormat("dd-mm-yyyy");
    }

    @Bean
    public Map<String, Mount> trollMountMap() {
        Map<String, Mount> mountMap = new HashMap<>();
        mountMap.put("m1", frostWolf());
        mountMap.put("m2", shadowTiger());
        return mountMap;
    }

    @Bean
    public Set<Mount> trollMountSet() {
        final Set<Mount> mounts = new LinkedHashSet<>();
        mounts.add(shadowTiger());
        mounts.add(frostWolf());
        return mounts;
    }

    @Value("${character.created}")
    private Date characterCreated;

    @Bean
    public Unit zulJin() {
        Troll troll = new Troll();
        troll.setColorCode(java.util.concurrent.ThreadLocalRandom.current().nextInt(2, 10));
        troll.setCreationDate(characterCreated);
        troll.setListOfMounts(Arrays.asList(Troll.DEFAULT_MOUNT, null, shadowTiger()));
        troll.setSetOfMounts(trollMountSet());
        troll.setMapOfMounts(trollMountMap());
        return troll;
    }
}
