CREATE TABLE project.supply_request (
                                       request_id SERIAL PRIMARY KEY,
                                       executive_commander_id INTEGER,
                                       executive_group_id INTEGER,
                                       role_of_executive_commander VARCHAR(255),
                                       date_of_request DATE,
                                       execution_complition_date DATE,
                                       status VARCHAR(255) NOT NULL ,
                                       resources_request_id INTEGER UNIQUE NOT NULL
);

CREATE TABLE project.resources_request (
                                           resources_request_id SERIAL PRIMARY KEY,
                                           commander_id INTEGER NOT NULL ,
                                           military_group_Id INTEGER NOT NULL ,
                                           role_Of_Commander VARCHAR(255) NOT NULL,
                                           ammo_556x45ar_count INTEGER,
                                           ammo_545x39ak_rpk_count INTEGER,
                                           ammo_762x39ak_count INTEGER,
                                           ammo_145kpvt_count INTEGER,
                                           ammo_40mm_rpg_count INTEGER,
                                           ammo_40mm_gp_count INTEGER,
                                           ammo_762pkt INTEGER,
                                           defensive_grenades_count INTEGER,
                                           offensive_grenades_count INTEGER,
                                           body_armor_count INTEGER,
                                           helmets_count INTEGER,
                                           rifles_count INTEGER,
                                           machine_guns_count INTEGER,
                                           dry_rations_count INTEGER,
                                           food_count INTEGER,
                                           tank_count INTEGER,
                                           apc_count INTEGER,
                                           exact_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
