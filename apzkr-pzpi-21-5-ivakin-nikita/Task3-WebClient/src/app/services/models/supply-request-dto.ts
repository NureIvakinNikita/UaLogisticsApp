/* tslint:disable */
/* eslint-disable */
import { ResourcesRequest } from '../models/resources-request';
export interface SupplyRequestDto {
  commanderId?: number;
  dateOfRequest?: string;
  'executionСomplitionDate'?: string;
  executiveCommanderId?: number;
  executiveGroupId?: number;
  militaryGroupId?: number;
  requestId?: number;
  resourcesRequestId?: ResourcesRequest;
  roleOfCommander?: 'BRIGADE_COMMANDER' | 'BATTALION_COMMANDER' | 'COMPANY_COMMANDER' | 'PLAT_COMMANDER' | 'LOGISTIC_COMMANDER' | 'SCANNING_DEVICE' | 'ADMIN';
  roleOfExecutiveCommander?: 'BRIGADE_COMMANDER' | 'BATTALION_COMMANDER' | 'COMPANY_COMMANDER' | 'PLAT_COMMANDER' | 'LOGISTIC_COMMANDER' | 'SCANNING_DEVICE' | 'ADMIN';
  seniorMilitaryGroupId?: number;
  status?: 'NOT_PROCESSED' | 'EXECUTING' | 'GIVEN_TO_BRIGADE' | 'FINISHED';
}
