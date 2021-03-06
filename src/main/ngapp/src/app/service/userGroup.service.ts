// angular > core
import {Inject, Injectable} from "@angular/core";
// angular > http
import {Http, RequestMethod} from "@angular/http";
// model > entity
import {UserGroup} from "../model/entity/userGroup";
// model > filter
import {UserGroupFilter} from "../model/filter/userGroup-filter";
// service
import {AbstractService} from "./abstract-service";
// util
import {USER_GROUP_SERVICE_URL} from "../util/injectable-constants";
// rxjs
import {Observable} from "rxjs/Observable";

@Injectable()
export class UserGroupService extends AbstractService {

	public constructor(http: Http, @Inject(USER_GROUP_SERVICE_URL) baseUrl: string) {
		super(http, baseUrl);
	}

	public add(userGroup: UserGroup): Observable<UserGroup> {
		return super.httpRequest(RequestMethod.Post, "add", userGroup);
	}

	public update(userGroup: UserGroup): Observable<UserGroup> {
		return super.httpRequest(RequestMethod.Post, "update", userGroup);
	}

	public delete(id: number): Observable<void> {
		return super.httpRequest(RequestMethod.Delete, `delete/${id}`);
	}

	public get(id: number): Observable<UserGroup> {
		return super.httpRequest(RequestMethod.Get, `get/${id}`);
	}

	public getCount(filter: UserGroupFilter): Observable<number> {
		return super.httpRequest(RequestMethod.Post, "getCount", filter);
	}

	public getList(filter: UserGroupFilter): Observable<UserGroup[]> {
		return super.httpRequest(RequestMethod.Post, "getList", filter);
	}
}