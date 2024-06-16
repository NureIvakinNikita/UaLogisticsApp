/* tslint:disable */
/* eslint-disable */
import { PlatGroupDto } from '../models/plat-group-dto';
export interface PlatCommanderDto {
  age?: number;
  email?: string;
  firstName?: string;
  id?: number;
  lastName?: string;
  passportNumber?: string;
  platGroup?: PlatGroupDto;
  platGroupId?: number;
  post?: 'BRIGADE_COMMANDER' | 'REGIMENT_COMMANDER' | 'BATTALION_COMMANDER' | 'COMPANY_COMMANDER' | 'PLAT_COMMANDER' | 'LOGISTIC_COMMANDER';
  rank?: 'MAJOR_GENERAL' | 'COLONEL' | 'MAJOR' | 'LIEUTENANT_COLONEL' | 'SENIOR_LIEUTENANT' | 'JUNIOR_LIEUTENANT' | 'SCANNING_DEVICE';
  role?: 'BRIGADE_COMMANDER' | 'BATTALION_COMMANDER' | 'COMPANY_COMMANDER' | 'PLAT_COMMANDER' | 'LOGISTIC_COMMANDER' | 'SCANNING_DEVICE' | 'ADMIN';
  secondName?: string;
}
