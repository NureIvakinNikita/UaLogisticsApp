/* tslint:disable */
/* eslint-disable */
export interface ResourcesRequestDto {
  ammo145KpvtCount?: number;
  ammo40mmGpCount?: number;
  ammo40mmRpgCount?: number;
  ammo545x39AkRpkCount?: number;
  ammo556x45ArCount?: number;
  ammo762PktCount?: number;
  ammo762x39AkCount?: number;
  apcCount?: number;
  bodyArmorCount?: number;
  commanderId: number;
  defensiveGrenadesCount?: number;
  dryRationsCount?: number;
  foodCount?: number;
  helmetsCount?: number;
  machineGunsCount?: number;
  militaryGroupId: number;
  offensiveGrenadesCount?: number;
  riflesCount?: number;
  roleOfCommander: 'BRIGADE_COMMANDER' | 'BATTALION_COMMANDER' | 'COMPANY_COMMANDER' | 'PLAT_COMMANDER' | 'LOGISTIC_COMMANDER' | 'SCANNING_DEVICE' | 'ADMIN';
  tankCount?: number;
}
