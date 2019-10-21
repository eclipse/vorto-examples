import { Injectable } from '@angular/core';

@Injectable()
export class Plugin {
    id: string;
    name: string;
    description: string;
    topic: string;
    imageUrl: string;
    credentials: string;
    isActivated: boolean;
    isUpdating: boolean;
    configuration: any;
    isExtended: boolean;

    constructor(id: string) {
        this.id = id
        this.name = undefined
        this.description = undefined
        this.imageUrl = undefined
        this.credentials = undefined
        this.isActivated = undefined
        this.isUpdating = false
        this.configuration = new Map()
    }

    getConfiguration(configuration) {
        if (Object.keys(configuration).length !== 0) {
            this.configuration = new Map(Object.entries(configuration));
        }
        else {
            this.configuration.set('no configuration provided', '')
        }
    }
    hidePassword() {
        if (this.configuration.has('password')) {
            let pwField = this.configuration.get('password') ? this.configuration.get('password') : {}
                pwField['value'] = (pwField['value']) ?
                    '*'.repeat(pwField['value'].length) :
                    ''
            }
    }


    activate() {
        this.isActivated = true
    }

    deactivate() {
        this.isActivated = false
    }


}
