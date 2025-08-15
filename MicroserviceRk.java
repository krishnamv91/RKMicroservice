public class MicroserviceRk
{
  public static void main(String[] args)
	{
		System.out.println("RK Microservice - Starting Application");

		// Check if IBS batch process should be executed
		if (args.length > 0 && "batch".equalsIgnoreCase(args[0])) {
			executeIBSBatchProcess();
		} else {
			// Default application behavior
			System.out.println("Application running in standard mode");
			System.out.println("Use 'java MicroserviceRk batch' to run IBS batch process");
		}

		System.out.println("RK Microservice - Application completed");
	}

	/**
	 * Execute IBS batch processing
	 */
	private static void executeIBSBatchProcess() {
		System.out.println("=== Starting IBS Batch Process ===");
		
		try {
			IBSBatchProcess batchProcess = new IBSBatchProcess();
			batchProcess.executeBatch();
			
			// Display final statistics
			IBSBatchProcess.BatchStats stats = batchProcess.getBatchStats();
			System.out.println("\n=== BATCH EXECUTION SUMMARY ===");
			System.out.println("Batch completed successfully!");
			System.out.println("Success Rate: " + String.format("%.2f%%", stats.getSuccessRate()));
			
		} catch (Exception e) {
			System.err.println("IBS Batch Process failed: " + e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println("=== IBS Batch Process Completed ===");
	}
}
