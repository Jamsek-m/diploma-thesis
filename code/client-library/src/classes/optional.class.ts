export class Optional<T> {

    private value: T;

    public static empty(): Optional<any> {
        return new Optional<any>();
    }

    public static withValue<T>(value: T): Optional<T> {
        const optional = new Optional<T>();
        optional.value = value;
        return optional;
    }

    public isPresent(): boolean {
        return !!this.value;
    }

    public get(): T {
        return this.value;
    }

    public getOrElse(fallbackValue: T): T {
        if (this.isPresent()) {
            return this.value;
        }
        return fallbackValue;
    }

    public getOrThrow(throwable: Error): T {
        if (this.isPresent()) {
            return this.value;
        }
        throw throwable;
    }

}
