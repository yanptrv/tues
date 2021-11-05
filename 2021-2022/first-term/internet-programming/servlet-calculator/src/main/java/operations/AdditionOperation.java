package operations;

import java.util.List;

public class AdditionOperation extends AbstractOperation {
	AdditionOperation(List<String> arguments) {
		super(arguments);
	}

	@Override
	public double perform() {
		return arguments.stream().reduce(0., Double::sum);
	}
}
