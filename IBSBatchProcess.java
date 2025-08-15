import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * IBS (Integrated Banking System) Batch Process
 * Handles batch operations for banking transactions and account processing
 */
public class IBSBatchProcess {
    
    private static final String BATCH_ID_PREFIX = "IBS_BATCH_";
    private final String batchId;
    private final LocalDateTime startTime;
    private int processedTransactions = 0;
    private int successfulTransactions = 0;
    private int failedTransactions = 0;
    private List<String> processLog = new ArrayList<>();
    
    public IBSBatchProcess() {
        this.startTime = LocalDateTime.now();
        this.batchId = generateBatchId();
        logMessage("IBS Batch Process initialized with ID: " + batchId);
    }
    
    /**
     * Generate unique batch ID with timestamp
     */
    private String generateBatchId() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        return BATCH_ID_PREFIX + timestamp;
    }
    
    /**
     * Main batch execution method
     */
    public void executeBatch() {
        logMessage("Starting IBS batch execution...");
        
        try {
            // Step 1: Data Validation
            validateInputData();
            
            // Step 2: Process Transactions
            processTransactions();
            
            // Step 3: Update Account Balances
            updateAccountBalances();
            
            // Step 4: Generate Reports
            generateReports();
            
            logMessage("IBS batch execution completed successfully");
            
        } catch (Exception e) {
            logMessage("Error during batch execution: " + e.getMessage());
            throw new RuntimeException("Batch processing failed", e);
        }
    }
    
    /**
     * Validate input data before processing
     */
    private void validateInputData() {
        logMessage("Validating input data...");
        
        // Simulate data validation
        try {
            Thread.sleep(500); // Simulate processing time
            logMessage("Data validation completed - All input data is valid");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Data validation interrupted", e);
        }
    }
    
    /**
     * Process banking transactions in batch
     */
    private void processTransactions() {
        logMessage("Processing transactions...");
        
        // Simulate processing multiple transactions
        Random random = new Random();
        int transactionCount = 10 + random.nextInt(20); // Process 10-30 transactions
        
        for (int i = 1; i <= transactionCount; i++) {
            processedTransactions++;
            
            // Simulate transaction processing with 90% success rate
            if (random.nextDouble() < 0.9) {
                successfulTransactions++;
                logMessage("Transaction " + i + " processed successfully");
            } else {
                failedTransactions++;
                logMessage("Transaction " + i + " failed - Invalid account details");
            }
            
            // Simulate processing delay
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Transaction processing interrupted", e);
            }
        }
        
        logMessage("Transaction processing completed. Successful: " + successfulTransactions + 
                  ", Failed: " + failedTransactions);
    }
    
    /**
     * Update account balances based on processed transactions
     */
    private void updateAccountBalances() {
        logMessage("Updating account balances...");
        
        try {
            Thread.sleep(800); // Simulate balance update processing
            logMessage("Account balances updated for " + successfulTransactions + " transactions");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Balance update interrupted", e);
        }
    }
    
    /**
     * Generate batch processing reports
     */
    private void generateReports() {
        logMessage("Generating batch reports...");
        
        try {
            Thread.sleep(300); // Simulate report generation
            
            // Generate summary report
            StringBuilder report = new StringBuilder();
            report.append("\n=== IBS BATCH PROCESSING REPORT ===\n");
            report.append("Batch ID: ").append(batchId).append("\n");
            report.append("Start Time: ").append(startTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)).append("\n");
            report.append("End Time: ").append(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)).append("\n");
            report.append("Total Transactions Processed: ").append(processedTransactions).append("\n");
            report.append("Successful Transactions: ").append(successfulTransactions).append("\n");
            report.append("Failed Transactions: ").append(failedTransactions).append("\n");
            report.append("Success Rate: ").append(String.format("%.2f%%", 
                (successfulTransactions * 100.0 / processedTransactions))).append("\n");
            report.append("=====================================");
            
            logMessage(report.toString());
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Report generation interrupted", e);
        }
    }
    
    /**
     * Log message with timestamp
     */
    private void logMessage(String message) {
        String timestampedMessage = "[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "] " + message;
        processLog.add(timestampedMessage);
        System.out.println(timestampedMessage);
    }
    
    /**
     * Get batch processing statistics
     */
    public BatchStats getBatchStats() {
        return new BatchStats(batchId, processedTransactions, successfulTransactions, failedTransactions, startTime);
    }
    
    /**
     * Get complete process log
     */
    public List<String> getProcessLog() {
        return new ArrayList<>(processLog);
    }
    
    /**
     * Inner class to hold batch statistics
     */
    public static class BatchStats {
        private final String batchId;
        private final int totalTransactions;
        private final int successfulTransactions;
        private final int failedTransactions;
        private final LocalDateTime startTime;
        
        public BatchStats(String batchId, int totalTransactions, int successfulTransactions, 
                         int failedTransactions, LocalDateTime startTime) {
            this.batchId = batchId;
            this.totalTransactions = totalTransactions;
            this.successfulTransactions = successfulTransactions;
            this.failedTransactions = failedTransactions;
            this.startTime = startTime;
        }
        
        // Getters
        public String getBatchId() { return batchId; }
        public int getTotalTransactions() { return totalTransactions; }
        public int getSuccessfulTransactions() { return successfulTransactions; }
        public int getFailedTransactions() { return failedTransactions; }
        public LocalDateTime getStartTime() { return startTime; }
        public double getSuccessRate() { 
            return totalTransactions > 0 ? (successfulTransactions * 100.0 / totalTransactions) : 0.0; 
        }
    }
}