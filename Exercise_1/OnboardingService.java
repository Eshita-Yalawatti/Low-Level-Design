import java.util.*;

public class OnboardingService {

    private final StudentRepository repo;
    private final StudentRawParser parser;
    private final StudentValidator validator;
    private final OnboardingPrinter printer;

    // Main.java remains unchanged
    public OnboardingService(FakeDb db) {
        this(
                (StudentRepository) db,
                new StudentRawParser(),
                new StudentValidator(),
                new OnboardingPrinter()
        );
    }

    // cleaner DI constructor
    public OnboardingService(
            StudentRepository repo,
            StudentRawParser parser,
            StudentValidator validator,
            OnboardingPrinter printer) {

        this.repo = repo;
        this.parser = parser;
        this.validator = validator;
        this.printer = printer;
    }

    // ONLY orchestration now
    public void registerFromRawInput(String raw) {

        printer.printInput(raw);

        Map<String, String> kv = parser.parse(raw);

        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");

        List<String> errors =
                validator.validate(name, email, phone, program);

        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(repo.count());

        StudentRecord rec =
                new StudentRecord(id, name, email, phone, program);

        repo.save(rec);

        printer.printSuccess(rec, repo.count());
    }
}
