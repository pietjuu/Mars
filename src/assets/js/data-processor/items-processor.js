
class ItemsProcessor {

    COLUMN_NAMES = {
        id: "id",
        name: "name",
        action: "action",
        dateSent: "dateSent",
        timeSent: "timeSent",
        dateReceived: "dateReceived",
        timeReceived: "timeReceived",
        receiver: "receiver",
        origin: "origin",
        sender: "sender",
        destination: "destination"
    };


    constructor(items) {
        this.items = this.getItemsDateTimeSeperated(items);

        // eslint-disable-next-line no-restricted-syntax
        Array.prototype.rreduce = function rreduce(fct, initial) {
            return this.reduce((a, b) => fct(b, a), initial);
        };
        Array.prototype.sum = function sum() {
            return this.reduce((prev, cur) => prev + cur);
        };
        Array.prototype.splitBy = function splitBy(key, combineFunction) {
            function splitterFunction(key, combineFunction) {
                return (cur, acc = {}) => {
                    acc[cur[key]] = combineFunction(cur, acc[cur[key]]);
                    return acc;
                };
            }

            return this.rreduce(splitterFunction(key, combineFunction), {});
        };
        Array.prototype.advancedFilter = function advancedFilter(matchParameters) {
            function simpleMatch(key, value) {
                if (Array.isArray(value)) {
                    return element => value.includes(element[key]);
                }
                return element => element[key] === value;
            }

            function combineMatch(f1, f2) {
                return element => f1(element) && f2(element);
            }

            function matchFunction(object) {
                let result = () => true;
                // eslint-disable-next-line guard-for-in
                for (const key in object) {
                    result = combineMatch(result, simpleMatch(key, object[key]));
                }
                return result;
            }
            return this.filter(matchFunction(matchParameters));
        };
        Array.prototype.unique = function() {
            const a = this.concat();
            for(let i=0; i<a.length; ++i) {
                for(let j=i+1; j<a.length; ++j) {
                    if(a[i] === a[j]) {
                        a.splice(j--, 1);
                    }
                }
            }

            return a;
        };
    }

    addCount(b, a = 0) {
        return a + b.COUNT;
    }

    countItems(b, a = 0) {
        return a + 1;
    }

    calculatePercent(counts, totals) {
        const result = {};

        // eslint-disable-next-line guard-for-in
        for (const key in counts) {
            result[key] = (100 * counts[key]) / totals[key];
        }

        return result;
    }

    cumulDays(data) {
        const { result: total } = Object.keys(data).sort().reduce((acc, cur) => {
            const { result } = acc;
            let { sum } = acc;
            sum += data[cur];
            result[cur] = sum;
            return { result, sum };
        }, { result: {}, sum: 0 });

        return total;
    }

    getItemsDateTimeSeperated(items){
        return items.map( item => {
            const container = {...item};
            const dateTimeSent = item.timeSent.split(" ");
            const dateTimeReceived = item.timeReceived.split(" ");

            container.dateSent = dateTimeSent[0];
            container.timeSent = dateTimeSent[1];
            container.dateReceived = dateTimeReceived[0];
            container.timeReceived = dateTimeReceived[1];

            return container;
        });
    }

    getAllDays() {
        const allSentDates = Array.from(new Set(this.items.map((item) => item.dateSent)));
        const allReceivedDates = Array.from(new Set(this.items.map((item) => item.dateReceived)));
        return allSentDates.concat(allReceivedDates).unique(); // distinct merge both arrays
    }

    getSentItemsPerDay() {
        const matchParameters = {
            action: ["sent"]
        };

        return this.items.advancedFilter(matchParameters).splitBy(this.COLUMN_NAMES.dateSent, this.countItems);
    }

    getReceivedItemsPerDay() {
        const matchParameters = {
            action: ["received"]
        };

        return this.items.advancedFilter(matchParameters).splitBy(this.COLUMN_NAMES.dateReceived, this.countItems);
    }

    getAllItemsPerDay() {
        const matchParameters = {
            action: ["sent", "received"]
        };

        return this.items.advancedFilter(matchParameters).splitBy(this.COLUMN_NAMES.dateSent, this.countItems);
    }

    getSentItemsOvertime() {
        const sentItemsPerDay = this.getSentItemsPerDay();
        return this.cumulDays(sentItemsPerDay);
    }

    getReceivedItemsOvertime() {
        const receivedItemsPerDay = this.getReceivedItemsPerDay();
        return this.cumulDays(receivedItemsPerDay);
    }

    getAllItemsOvertime() {
        const allItemsPerDay = this.getAllItemsPerDay();
        return this.cumulDays(allItemsPerDay);
    }
}

export { ItemsProcessor };
