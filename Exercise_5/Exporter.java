public abstract class Exporter {
    public final ExportResult export(ExportRequest req) {
        if (req == null) throw new IllegalArgumentException("request is null");

        ExportResult out = doExport(req);

        if (out == null || out.contentType == null || out.bytes == null) {
            throw new IllegalStateException("invalid export result");
        }
        return out;
    }

    protected abstract ExportResult doExport(ExportRequest req);
}