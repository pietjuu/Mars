
class ItemsProcessor {

    COLUMN_NAMES = {
        id: "id",
        name: "name",
        action: "action",
        dateSent: "dateSent",
        dateReceived: "dateReceived",
        receiver: "receiver",
        origin: "origin",
        sender: "sender",
        destination: "destination"
    };


    constructor(items) {
        this.items = this.convertDates(items);

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

    convertDates(items){
        return items.map( item => {
            const container = {...item};

            container.dateSent = new Date(item.timeSent.split(" ")[0]).toISOString();
            container.dateReceived = new Date(item.timeReceived.split(" ")[0]).toISOString();

            return container;
        });
    }

    getStartDate(dates) {
        let startDate;
        dates.forEach(elem => {
            const date = new Date(elem);
            if(startDate === undefined || date < startDate) {
                startDate = date;
            }
        });
        return startDate;
    }

    getEndDate(dates) {
        let endDate;
        dates.forEach(elem => {
            const date = new Date(elem);
            if(endDate === undefined || date > endDate) {
                endDate = date;
            }
        });
        return endDate;
    }

    getDatesInRange(startDate, endDate) {
        const date = new Date(startDate.getTime());
        const dates = [];
        while (date <= endDate) {
            dates.push(new Date(date).toISOString());
            date.setDate(date.getDate() + 1);
        }
        return dates;
    }

    getAllDays() {
        const allSentDates = Array.from(new Set(this.items.map((item) => item.dateSent)));
        const allReceivedDates = Array.from(new Set(this.items.map((item) => item.dateReceived)));
        const allDates = allSentDates.concat(allReceivedDates).unique(); // distinct merge both arrays
        return this.getDatesInRange(this.getStartDate(allDates), this.getEndDate(allDates));
    }

    completeDataWithAllLabels(dateLabels, data) {
        const result = {...data};
        dateLabels.forEach(label => {
            if(data[label] === undefined) {
                result[label] = 0;
            }
        });
        return result;
    }

    getSentItemsPerDay() {
        const matchParameters = {
            action: ["sent"]
        };

        const sentItemsPerDay = this.items.advancedFilter(matchParameters).splitBy(this.COLUMN_NAMES.dateSent, this.countItems);
        return this.completeDataWithAllLabels(this.getAllDays(), sentItemsPerDay);
    }

    getReceivedItemsPerDay() {
        const matchParameters = {
            action: ["received"]
        };

        const receivedItemsPerDay = this.items.advancedFilter(matchParameters).splitBy(this.COLUMN_NAMES.dateReceived, this.countItems);
        return this.completeDataWithAllLabels(this.getAllDays(), receivedItemsPerDay);
    }

    getMostUsedDestinations() {
        const usagesPerDestination = this.items.splitBy(this.COLUMN_NAMES.destination, this.countItems);
        return usagesPerDestination;
    }
}

export { ItemsProcessor };
