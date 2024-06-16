CREATE SCHEMA IF NOT EXISTS project;

CREATE TABLE project.given_resources (
                                         given_resources_id SERIAL PRIMARY KEY,
                                         commander_id INTEGER,
                                         military_group_id INTEGER NOT NULL,
                                         brigade_commander_id INTEGER NOT NULL,
                                         role_of_commander VARCHAR(255),
                                         ammo_556x45ar_count INTEGER,
                                         ammo_545x39ak_rpk_count INTEGER,
                                         ammo_762x39ak_count INTEGER,
                                         ammo_145kpvt_count INTEGER,
                                         ammo_40mm_rpg_count INTEGER,
                                         ammo_40mm_gp_count INTEGER,
                                         ammo_762pkt_count INTEGER,
                                         defensive_grenades_count INTEGER,
                                         offensive_grenades_count INTEGER,
                                         body_armor_count INTEGER,
                                         helmets_count INTEGER,
                                         rifles_count INTEGER,
                                         machine_guns_count INTEGER,
                                         dry_rations_count INTEGER,
                                         food_count INTEGER,
                                         tank_count INTEGER,
                                         apc_count INTEGER
);