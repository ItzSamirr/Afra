package it.itzsamirr.afra.api;

public final class AfraProvider {
    private static AfraAPI api;

    private AfraProvider() {throw new UnsupportedOperationException();}

    public static void setApi(AfraAPI api) {
        AfraProvider.api = api;
    }

    public static AfraAPI getApi() {
        return api;
    }
}
