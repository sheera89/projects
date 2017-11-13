/**
 * Sandeep Heera
 * 6/1/2017
 * WorkoutGUI.java
 * This program is a standalone GUI which interfaces with a mysql server. It 
 * performs various operations on the mysql database via a graphical interface.
 */
package WorkoutTrackingSystem;
import java.sql.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * This class is a standalone GUI which interfaces with the workouts database.
 * It performs various tasks including insertions, deletions, and updates via a
 * graphical interface and a JDBC driver which is utilized to connect to the 
 * mysql server.
 * 
 * @author Sandeep Heera
 */
public class WorkoutGUI extends javax.swing.JFrame {
    private final int MAX_SETS_REPS = 10;
    private final int MAX_QUANTITY = 5;
    private final String[] EXERCISE_TYPES = {"Weight Lifting", "Cardio"};
    private final String[] FOOD_TYPES = {"Sandwiches", "Fruits", "Beef",
                                         "Burritos", "Chicken", "Fish",
                                         "Grains", "Pizza", "Salads",
                                         "Vegetables"};
    private final String[] DRINK_TYPES = {"Beer", "Soda", "Water", "Milk"};
    private final String CARDIO_MUSCLE_GROUPS[] = {"Heart", "Lungs"};
    private final String[] WEIGHT_LIFTING_MUSCLE_GROUPS = {"Arms", "Chest",
                                                           "Legs", "Back"};
    private final int EXERCISE_TYPE_CHANGED = 0;
    private final int MUSCLE_GROUP_CHANGED = 1;
    private java.awt.CardLayout cards;
    private DatabaseManager dm;
    private Connection connection;
    private String currentUserID;
	
	//GUI variables
    private javax.swing.JDialog accountLoginErrorDialog;
    private javax.swing.JScrollPane accountLoginErrorScrollPane;
    private javax.swing.JTextArea accountLoginErrorTextArea;
    private javax.swing.JButton addDrinkButton;
    private javax.swing.JLabel addDrinkLabel;
    private javax.swing.JButton addExerciseButton;
    private javax.swing.JComboBox<String> addExerciseComboBox;
    private javax.swing.JLabel addExerciseComboBoxLabel;
    private javax.swing.JLabel addExerciseLabel;
    private javax.swing.JButton addFoodButton;
    private javax.swing.JLabel addFoodLabel;
    private javax.swing.JButton createOrModifyWorkoutButton;
    private javax.swing.JButton deleteExerciseButton;
    private javax.swing.JComboBox<String> deleteExerciseComboBox;
    private javax.swing.JLabel deleteExerciseComboBoxLabel;
    private javax.swing.JLabel deleteExerciseLabel;
    private javax.swing.JLabel deleteExerciseSelectLabel;
    private javax.swing.JButton dietMainMenuButton;
    private javax.swing.JLabel dietMenuLabel;
    private javax.swing.JPanel dietMenuPanel;
    private javax.swing.JButton dietOptionButton;
    private javax.swing.JComboBox<String> drinkNameComboBox;
    private javax.swing.JLabel drinkNameLabel;
    private javax.swing.JComboBox<String> drinkQuantityComboBox;
    private javax.swing.JLabel drinkQuantityLabel;
    private javax.swing.JComboBox<String> drinkTypeComboBox;
    private javax.swing.JLabel drinkTypeLabel;
    private javax.swing.JComboBox<String> drinkWorkoutSelectComboBox;
    private javax.swing.JLabel drinkWorkoutSelectLabel;
    private javax.swing.JComboBox<String> exerciseNameComboBox;
    private javax.swing.JComboBox<String> exerciseSelectComboBox;
    private javax.swing.JLabel exerciseSelectComboBoxLabel;
    private javax.swing.JLabel exerciseSelectLabel;
    private javax.swing.JComboBox<String> exerciseTypeComboBox;
    private javax.swing.JLabel exerciseTypeLabel;
    private javax.swing.JComboBox<String> foodNameComboBox;
    private javax.swing.JLabel foodNameLabel;
    private javax.swing.JComboBox<String> foodQuantityComboBox;
    private javax.swing.JLabel foodQuantityLabel;
    private javax.swing.JComboBox<String> foodTypeComboBox;
    private javax.swing.JLabel foodTypeLabel;
    private javax.swing.JComboBox<String> foodWorkoutSelectComboBox;
    private javax.swing.JLabel foodWorkoutSelectLabel;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JLabel mainMenuLabel;
    private javax.swing.JPanel mainMenuPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel measurementsLabel;
    private javax.swing.JButton measurementsMainMenuButton;
    private javax.swing.JButton measurementsOptionButton;
    private javax.swing.JPanel measurementsPanel;
    private javax.swing.JScrollPane measurementsScrollPane;
    private javax.swing.JTable measurementsTable;
    private javax.swing.JLabel modifyWorkoutsLabel;
    private javax.swing.JButton modifyWorkoutsMainMenuButton;
    private javax.swing.JPanel modifyWorkoutsPanel;
    private javax.swing.JComboBox<String> muscleGroupComboBox;
    private javax.swing.JLabel muscleGroupLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JComboBox<String> repsComboBox;
    private javax.swing.JLabel repsLabel;
    private javax.swing.JComboBox<String> setsComboBox;
    private javax.swing.JLabel setsLabel;
    private javax.swing.JLabel userIDLabel;
    private javax.swing.JTextField userIDTextField;
    private javax.swing.JLabel userNameLabel;
    private javax.swing.JLabel workoutDetailsLabel;
    private javax.swing.JScrollPane workoutDetailsScrollPane;
    private javax.swing.JTable workoutDetailsTable;
    private javax.swing.JButton workoutLogMainMenuButton;
    private javax.swing.JLabel workoutLogMenuLabel;
    private javax.swing.JButton workoutLogOptionButton;
    private javax.swing.JPanel workoutLogPanel;
    private javax.swing.JScrollPane workoutLogScrollPane;
    private javax.swing.JTable workoutLogTable;
    private javax.swing.JPanel workoutMenuPanel;
    private javax.swing.JLabel workoutNameLabel;
    private javax.swing.JButton workoutOptionButton;
    private javax.swing.JComboBox<String> workoutSelectDropdownMenu;
    private javax.swing.JLabel workoutSelectLabel;
    private javax.swing.JButton workoutsMainMenuButton;
    private javax.swing.JLabel workoutsMenuLabel;
    
	/**
     * Sets the workout comboBoxes when a change is made. The affected comboBoxes
     * will depend on the argument.
     * 
     * @param changed integer corresponding to which comboBox changed
     */
    private void setWorkoutComboBoxes(int changed){
        if(changed == EXERCISE_TYPE_CHANGED){
            String exerciseType = exerciseTypeComboBox.getSelectedItem().toString();
            Statement s;
            ResultSet set;
            if(exerciseType.equals(EXERCISE_TYPES[0])){
                muscleGroupComboBox.setModel(new javax.swing.DefaultComboBoxModel());
                muscleGroupComboBox.setModel(new javax.swing.DefaultComboBoxModel(WEIGHT_LIFTING_MUSCLE_GROUPS));
                String muscleGroup = muscleGroupComboBox.getSelectedItem().toString();
                try {
                    dm.createConnection();
                    connection = dm.getAdminConnection();
                    s = connection.createStatement();
                    
                    //query the exercises associated with the exercise type
                    set = s.executeQuery("SELECT exercise_name FROM exercises WHERE exercise_type = " +
                                        "\"" + exerciseType + "\" AND " + 
                                        "targeted_muscle_group = \"" + muscleGroup + "\";");
                    int numResults = this.getNumRecords(set);

                    String[] exerciseNames = new String[numResults];
                    int i = 0;
                    set.beforeFirst();
                    //create a new DefaultComboBoxModel with elements from the acquired set
                    exerciseSelectComboBox.setModel(new javax.swing.DefaultComboBoxModel());

                    //iterate over workout names and populate the model
                    while(set.next()){
                        exerciseNames[i++] = set.getString("exercise_name");
                    }
                    exerciseSelectComboBox.setModel(new javax.swing.DefaultComboBoxModel(exerciseNames));

                    dm.closeConnection();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkoutGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(exerciseType.equals(EXERCISE_TYPES[1])){
                muscleGroupComboBox.setModel(new javax.swing.DefaultComboBoxModel());
                muscleGroupComboBox.setModel(new javax.swing.DefaultComboBoxModel(CARDIO_MUSCLE_GROUPS));
                String muscleGroup = muscleGroupComboBox.getSelectedItem().toString();
                try {
                    dm.createConnection();
                    connection = dm.getAdminConnection();
                    s = connection.createStatement();
                    //query the workout names associated with this ID
                    set = s.executeQuery("SELECT exercise_name FROM exercises WHERE exercise_type = " +
                                        "\"" + exerciseType + "\" AND " + 
                                        "targeted_muscle_group = \"" + muscleGroup + "\";");
                    int numResults = this.getNumRecords(set);
                    String[] exerciseNames = new String[numResults];
                    int i = 0;

                    //create a new DefaultComboBoxModel with elements from the acquired set
                    exerciseSelectComboBox.setModel(new javax.swing.DefaultComboBoxModel());
                    set.beforeFirst();
                    //iterate over workout names and populate the model
                    while(set.next()){
                        exerciseNames[i++] = set.getString("exercise_name");
                    }
                    exerciseSelectComboBox.setModel(new javax.swing.DefaultComboBoxModel(exerciseNames));

                    dm.closeConnection();
                } catch (SQLException ex) {
                    Logger.getLogger(WorkoutGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                muscleGroupComboBox.setModel(new javax.swing.DefaultComboBoxModel());
            }
        }
        else if (changed == MUSCLE_GROUP_CHANGED){
            String muscleGroup = muscleGroupComboBox.getSelectedItem().toString();
            String exerciseType = exerciseTypeComboBox.getSelectedItem().toString();
            Statement s;
            ResultSet set;
            try {
                dm.createConnection();
                connection = dm.getAdminConnection();
                s = connection.createStatement();
                //query the workout names associated with this ID
                set = s.executeQuery("SELECT exercise_name FROM exercises WHERE exercise_type = " +
                                    "\"" + exerciseType + "\" AND " + 
                                    "targeted_muscle_group = \"" + muscleGroup + "\";");
                int numResults = this.getNumRecords(set);
                String[] exerciseNames = new String[numResults];
                int i = 0;

                //create a new DefaultComboBoxModel with elements from the acquired set
                exerciseSelectComboBox.setModel(new javax.swing.DefaultComboBoxModel());
                set.beforeFirst();
                //iterate over workout names and populate the model
                while(set.next()){
                    exerciseNames[i++] = set.getString("exercise_name");
                }
                exerciseSelectComboBox.setModel(new javax.swing.DefaultComboBoxModel(exerciseNames));

                dm.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(WorkoutGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Sets the diet comboBoxes when a change is made.
     */
    private void setDietComboBoxes(){
        String foodType = foodTypeComboBox.getSelectedItem().toString();
        String drinkType = drinkTypeComboBox.getSelectedItem().toString();
        Statement s;
        ResultSet set;
        try {
            dm.createConnection();
            connection = dm.getAdminConnection();
            s = connection.createStatement();
            //query the workout names associated with this ID
            set = s.executeQuery("SELECT food_name FROM food WHERE food_category = " +
                                "\"" + foodType + "\";");
            int numResults = this.getNumRecords(set);

            String[] foodNames = new String[numResults];
            int i = 0;
            set.beforeFirst();
            //create a new DefaultComboBoxModel with elements from the acquired set
            foodNameComboBox.setModel(new javax.swing.DefaultComboBoxModel());

            //iterate over workout names and populate the model
            while(set.next()){
                foodNames[i++] = set.getString("food_name");
            }
            foodNameComboBox.setModel(new javax.swing.DefaultComboBoxModel(foodNames));
            
            //query the workout names associated with this ID
            set = s.executeQuery("SELECT drink_name FROM drinks WHERE type_of_drink = " +
                                "\"" + drinkType + "\";");
            numResults = this.getNumRecords(set);

            String[] drinkNames = new String[numResults];
            int j = 0;
            set.beforeFirst();
            //create a new DefaultComboBoxModel with elements from the acquired set
            drinkNameComboBox.setModel(new javax.swing.DefaultComboBoxModel());

            //iterate over workout names and populate the model
            while(set.next()){
                drinkNames[j++] = set.getString("drink_name");
            }
            drinkNameComboBox.setModel(new javax.swing.DefaultComboBoxModel(drinkNames));

            dm.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(WorkoutGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Populates the static comboBoxes in this GUI.
     */
    private void setConstantModels(){
        String[] setsAndRepsArr = new String[MAX_SETS_REPS];
        String[] quantityArr = new String[MAX_QUANTITY];
        
        for(int i = 0; i < MAX_SETS_REPS; i++){
            setsAndRepsArr[i] = Integer.toString(i + 1);
        }
        
        for(int j = 0; j < MAX_QUANTITY; j++){
            quantityArr[j] = Integer.toString(j + 1);
        }
         
        setsComboBox.setModel(new javax.swing.DefaultComboBoxModel(setsAndRepsArr));
        repsComboBox.setModel(new javax.swing.DefaultComboBoxModel(setsAndRepsArr));
        foodQuantityComboBox.setModel(new javax.swing.DefaultComboBoxModel(quantityArr));
        drinkQuantityComboBox.setModel(new javax.swing.DefaultComboBoxModel(quantityArr));
        exerciseTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(EXERCISE_TYPES));
        foodTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(FOOD_TYPES));
        drinkTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(DRINK_TYPES));
    }
    
    /**
     * Populates the measurements JTable.
     */
    private void populateMeasurementsTable(){
        Statement s;
        ResultSet set;
        try {
            dm.createConnection();
            connection = dm.getAdminConnection();
            s = connection.createStatement();
            
            //query the relevant data
            set = s.executeQuery("SELECT " +
                                " workout_name AS Workout," +
                                " waist AS Waist," +
                                " weight AS Weight," +
                                " height AS Height," +
                                " arms AS Arms" +
                                " FROM" +
                                " workouts" +
                                " INNER JOIN" +
                                " body_measurements ON workouts.workout_id = body_measurements.workout_id" +
                                " WHERE" +
                                " workouts.user_id = \"" + currentUserID + "\"" +
                                " AND body_measurements.user_id = \"" + currentUserID + "\"" +
                                " GROUP BY Workout;");
 
            this.resultSetToTableModel(set, measurementsTable);
            dm.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(WorkoutGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Adds the food item to the food_consumed table.
     * 
     * @param foodName name of the food item to be added
     * @param workoutName name of the workout corresponding to the food being consumed
     * @param quantity number of this food item eaten
     */
    private void addFood(String foodName, String workoutName, int quantity){
        //check to see if we have non-empty, non-null Strings
        if((foodName != null && !foodName.isEmpty()) && (workoutName != null &&
            !workoutName.isEmpty())){
            Statement s;
            ResultSet set;
            try {
                dm.createConnection();
                connection = dm.getAdminConnection();
                s = connection.createStatement();
                
                //find the food_id associated with the foodName
                set = s.executeQuery("SELECT food_id FROM food WHERE " +
                                     "food_name = \"" + foodName + "\";");
                set.next();
                int foodID = Integer.parseInt(set.getString("food_id"));
                
                //find the workout_id associated with the workoutName
                set = s.executeQuery("SELECT workout_id FROM workouts WHERE " +
                                     "workout_name = \"" + workoutName + "\" AND " +
                                      "user_id = \"" + currentUserID + "\";");
                set.next();
                int workoutID = Integer.parseInt(set.getString("workout_id"));

                //insert into food_consumed table
                s.executeUpdate("INSERT INTO food_consumed " +
                               "VALUES (\"" + currentUserID + "\", \"" + workoutID +
                               "\", \"" + foodID + "\", \"" + quantity + "\");");
                dm.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(WorkoutGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Adds the drink to the drinks_consumed table.
     * 
     * @param drinkName name of the drink to be added
     * @param workoutName name of the workout which corresponds to the drink
     * @param quantity number of drinks consumed
     */
    private void addDrink(String drinkName, String workoutName, int quantity){
        //check to see if we have non-empty, non-null Strings
        if((drinkName != null && !drinkName.isEmpty()) && (workoutName != null &&
            !workoutName.isEmpty())){
            Statement s;
            ResultSet set;
            try {
                dm.createConnection();
                connection = dm.getAdminConnection();
                s = connection.createStatement();
                
                //find the drink_id associated with the drinkName
                set = s.executeQuery("SELECT drink_id FROM drinks WHERE " +
                                     "drink_name = \"" + drinkName + "\";");
                set.next();
                int drinkID = Integer.parseInt(set.getString("drink_id"));
                
                //find the workout_id associated with the workoutName
                set = s.executeQuery("SELECT workout_id FROM workouts WHERE " +
                                     "workout_name = \"" + workoutName + "\" AND " +
                                      "user_id = \"" + currentUserID + "\";");
                set.next();
                int workoutID = Integer.parseInt(set.getString("workout_id"));

                //insert into drinks_consumed table
                s.executeUpdate("INSERT INTO drinks_consumed " +
                               "VALUES (\"" + currentUserID + "\", \"" + workoutID +
                               "\", \"" + drinkID + "\", \"" + quantity + "\");");
                dm.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(WorkoutGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
      
    /**
     * Adds the exercise to the workout.
     * 
     * @param exerciseName name of the exercise to be added
     * @param workoutName name of the workout to be modified
     * @param sets number of sets for the exercise
     * @param reps number of reps for the exercise
     */
    private void addExerciseToWorkout(String exerciseName, String workoutName,
                                      int sets, int reps){
        Statement s;
        ResultSet set;
        try {
            dm.createConnection();
            connection = dm.getAdminConnection();
            s = connection.createStatement();

            //find the exercise_id associated with the exercise_name
            set = s.executeQuery("SELECT exercise_id FROM exercises WHERE " +
                                 "exercise_name = \"" + exerciseName + "\";");
            set.next();
            int exerciseID = Integer.parseInt(set.getString("exercise_id"));

            //find the workout_id associated with the workoutName
            set = s.executeQuery("SELECT workout_id FROM workouts WHERE " +
                                 "workout_name = \"" + workoutName + "\" AND " +
                                  "user_id = \"" + currentUserID + "\";");
            set.next();
            int workoutID = Integer.parseInt(set.getString("workout_id"));

            //insert into workouts table
            s.executeUpdate("INSERT INTO workouts " +
                           "VALUES (\"" + currentUserID + "\", \"" + workoutID +
                           "\", \"" + exerciseID + "\", \"" + sets + "\", \"" + reps +
                           "\", \"" + workoutName + "\", 0, 0);");
            dm.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(WorkoutGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Deletes an exercise from the desired workout.
     * 
     * @param exerciseName name of the exercise to be deleted
     * @param workoutName name of the workout containing the exercise
     */
    private void deleteExercise(String exerciseName, String workoutName){
        Statement s;
        ResultSet set;
        try {
            dm.createConnection();
            connection = dm.getAdminConnection();
            s = connection.createStatement();

            //find the exercise_id associated with the exercise_name
            set = s.executeQuery("SELECT exercise_id FROM exercises WHERE " +
                                 "exercise_name = \"" + exerciseName + "\";");
            set.next();
            int exerciseID = Integer.parseInt(set.getString("exercise_id"));
   
            //find the workout_id associated with the workoutName
            set = s.executeQuery("SELECT workout_id FROM workouts WHERE " +
                                 "workout_name = \"" + workoutName + "\" AND " +
                                  "user_id = \"" + currentUserID + "\";");
            set.next();
            int workoutID = Integer.parseInt(set.getString("workout_id"));
       
            //delete from workouts table
            s.execute("SET FOREIGN_KEY_CHECKS=0;");
            s.executeUpdate("DELETE FROM workouts " +
                            "WHERE user_id = \"" + currentUserID + "\" AND " +
                            "workout_id = \"" + workoutID + "\" AND exercise_id = \"" +
                             exerciseID + "\";");
            s.execute("SET FOREIGN_KEY_CHECKS=1;");
            
            //repopulate the exercises comboBox
            set = s.executeQuery("SELECT exercise_name " + "FROM workouts INNER JOIN exercises "
                             + "on workouts.exercise_id = exercises.exercise_id where user_id = " +
                             "\"" + currentUserID + "\" AND " + 
                             "workout_name = \"" + workoutName + "\";");
            
            int numResults = this.getNumRecords(set);
            set.beforeFirst();
            String[] exerciseNames = new String[numResults];
            int j = 0;
            //create a new DefaultComboBoxModel with elements from the acquired set
            exerciseNameComboBox.setModel(new javax.swing.DefaultComboBoxModel());

            //iterate over exercise names and populate the model
            while(set.next()){
                exerciseNames[j++] = set.getString("exercise_name");
            }
            exerciseNameComboBox.setModel(new javax.swing.DefaultComboBoxModel(exerciseNames));
            dm.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(WorkoutGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    /**
     * Populates the workout logs JTable.
     */
    private void populateWorkoutLogsTable(){
        Statement s;
        ResultSet set;
        try {
            dm.createConnection();
            connection = dm.getAdminConnection();
            s = connection.createStatement();
            
            //query the relevant data from the tables
            set = s.executeQuery("SELECT " +
                                " workout_name AS Workout," +
                                " workout_length AS Workout_Length," +
                                " current_week AS Current_Week," +
                                " total_workouts_completed AS Total_Completed" +
                                " FROM" +
                                " workouts" +
                                " INNER JOIN" +
                                " workout_log ON workouts.workout_id = workout_log.workout_id" +
                                " WHERE" +
                                " workouts.user_id = \"" + currentUserID + "\"" +
                                " AND workout_log.user_id = \"" + currentUserID + "\"" +
                                " GROUP BY Workout;");
 
            this.resultSetToTableModel(set, workoutLogTable);
            dm.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(WorkoutGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method returns the number of elements in the ResultSet passed in
     * as an argument.
     * 
     * @param set ResultSet from which we want the length
     * @return integer containing the number of elements in the ResultSet
     * @throws SQLException 
     */
    private int getNumRecords(ResultSet set) throws SQLException{
        int size=0;
        while (set.next()) {
            size++;
        }   
        
        return size;
    }
	
	/**
     * This is a method found at 
     * https://stackoverflow.com/questions/10620448/most-simple-code-to-populate-jtable-from-resultset.
     * All credit goes to user "Zoka".
     * 
     * @author Zoka
     * @param rs ResultSet to be extracted from
     * @param table JTable to be populated
     * @throws SQLException 
     */
    private void resultSetToTableModel(ResultSet rs, JTable table) throws SQLException{
        //Create new table model
        DefaultTableModel tableModel = new DefaultTableModel();

        //Retrieve meta data from ResultSet
        ResultSetMetaData metaData = rs.getMetaData();

        //Get number of columns from meta data
        int columnCount = metaData.getColumnCount();

        //Get all column names from meta data and add columns to table model
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
            tableModel.addColumn(metaData.getColumnLabel(columnIndex));
        }

        //Create array of Objects with size of column count from meta data
        Object[] row = new Object[columnCount];

        //Scroll through result set
        while (rs.next()){
            //Get object from column with specific index of result set to array of objects
            for (int i = 0; i < columnCount; i++){
                row[i] = rs.getObject(i+1);
            }
            //Now add row to table model with that array of objects as an argument
            tableModel.addRow(row);
        }

        //Now add that table model to your table and you are done :D
        table.setModel(tableModel);
    }
	
    /**
     * Creates GUI.
	 
     * @throws java.sql.SQLException
     * @throws java.io.FileNotFoundException
     */
    public WorkoutGUI() throws SQLException, FileNotFoundException {
        initComponents();
        this.setConstantModels();
        dm = new DatabaseManager();
        cards = (java.awt.CardLayout)mainPanel.getLayout();
    }
	
	/**
     * Event handler for workout main menu button.
     * 
     * @param evt 
     */
    private void workoutOptionButtonMouseClicked(java.awt.event.MouseEvent evt) {
        Statement s;
        ResultSet set;
        try {
            dm.createConnection();
            connection = dm.getAdminConnection();
            s = connection.createStatement();
            //query the workout names associated with this ID
            set = s.executeQuery("SELECT workout_name " +
                                 "FROM workouts WHERE user_id = \"" +
                                  currentUserID + "\" GROUP BY workout_name;");
 
            //check to see if the user has any workouts 
            int numResults = this.getNumRecords(set);
            
            //reset iterator
            set.beforeFirst();
               
            //if no results are found, set the model to empty
            if(numResults == 0){
                workoutSelectDropdownMenu.setModel(new javax.swing.DefaultComboBoxModel());
            }
            else{
                String[] workoutNames = new String[numResults];
                int i = 0;
                //create a new DefaultComboBoxModel with elements from the acquired set
                workoutSelectDropdownMenu.setModel(new javax.swing.DefaultComboBoxModel());
                
                //iterate over workout names and populate the model
                while(set.next()){
                    workoutNames[i++] = set.getString("workout_name");
                }
                workoutSelectDropdownMenu.setModel(new javax.swing.DefaultComboBoxModel(workoutNames));
            }
            dm.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(WorkoutGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        cards.show(mainPanel, "workoutMenuCard");
    }
	
	/**
     * Event handler for diet main menu button.
     * 
     * @param evt 
     */
    private void dietOptionButtonMouseClicked(java.awt.event.MouseEvent evt) {
        Statement s;
        ResultSet set;
        try {
            dm.createConnection();
            connection = dm.getAdminConnection();
            s = connection.createStatement();
            //query the workout names associated with this ID
            set = s.executeQuery("SELECT workout_name " +
                                 "FROM workouts WHERE user_id = \"" +
                                  currentUserID + "\" GROUP BY workout_name;");
 
            //check to see if the user has any workouts 
            int numResults = this.getNumRecords(set);
            
            //reset iterator
            set.beforeFirst();
               
            //if no results are found, set the model to empty
            if(numResults == 0){
                foodWorkoutSelectComboBox.setModel(new javax.swing.DefaultComboBoxModel());
                drinkWorkoutSelectComboBox.setModel(new javax.swing.DefaultComboBoxModel());
            }
            else{
                String[] workoutNames = new String[numResults];
                int i = 0;
                
                //create a new DefaultComboBoxModel with elements from the acquired set
                foodWorkoutSelectComboBox.setModel(new javax.swing.DefaultComboBoxModel());
                drinkWorkoutSelectComboBox.setModel(new javax.swing.DefaultComboBoxModel());
                
                //iterate over workout names and populate the model
                while(set.next()){
                    workoutNames[i++] = set.getString("workout_name");
                }
                foodWorkoutSelectComboBox.setModel(new javax.swing.DefaultComboBoxModel(workoutNames));
                drinkWorkoutSelectComboBox.setModel(new javax.swing.DefaultComboBoxModel(workoutNames));
            }
            this.setDietComboBoxes();
            dm.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(WorkoutGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        cards.show(mainPanel, "dietMenuCard");
    }

    private void measurementsOptionButtonMouseClicked(java.awt.event.MouseEvent evt) {
        this.populateMeasurementsTable();
        cards.show(mainPanel, "measurementsCard");
    }

    private void workoutLogOptionButtonMouseClicked(java.awt.event.MouseEvent evt) {
        this.populateWorkoutLogsTable();
        cards.show(mainPanel, "workoutLogCard");
    }

    private void addExerciseButtonMouseClicked(java.awt.event.MouseEvent evt) {
        String exerciseName = exerciseSelectComboBox.getSelectedItem().toString();
        String workoutName = addExerciseComboBox.getSelectedItem().toString();
        int sets = Integer.parseInt(setsComboBox.getSelectedItem().toString());
        int reps = Integer.parseInt(repsComboBox.getSelectedItem().toString());
        addExerciseToWorkout(exerciseName, workoutName, sets, reps);
    }

    private void deleteExerciseButtonMouseClicked(java.awt.event.MouseEvent evt) {
        String exerciseName = exerciseNameComboBox.getSelectedItem().toString();
        String workoutName = deleteExerciseComboBox.getSelectedItem().toString();
        deleteExercise(exerciseName, workoutName);
    }
    
    /**
        This method is the event handler for the loginButtonMouseClicked button.
        When this button is pressed a query is made for the contents of the 
        userIDTextField text field to determine if the user's credentials are
        valid.
        
        @MouseEvent evt event corresponding to the click of the button
    */
    private void loginButtonMouseClicked(java.awt.event.MouseEvent evt) {
        String userName = userIDTextField.getText();
        Statement s;
        ResultSet set;
        try {
            dm.createConnection();
            connection = dm.getAdminConnection();
            s = connection.createStatement();
            //query the userID
            set = s.executeQuery("SELECT user_id, password, f_name, l_name " +
                                 "FROM users WHERE user_id = \"" +
                                  userName + "\";");
            
            //if the userID is incorrect, display the error dialog and reset the
            //text fields.
            if(!set.first()){
                accountLoginErrorDialog.setVisible(true);
                userIDTextField.setText("");
                passwordField.setText("");
            }
            else{
                //check to see if the password is valid
                String password = set.getString(2);
                if(passwordField.getText().equals(password)){
                    userNameLabel.setText("Hello " + set.getString(3) + " " +
                                           set.getString(4));
                    currentUserID = new String();
                    currentUserID += userName;
                    cards.show(mainPanel, "mainMenuCard");
                } 
                else {
                    accountLoginErrorDialog.setVisible(true);
                    userIDTextField.setText("");
                    passwordField.setText("");
                }
            }
            dm.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(WorkoutGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Event handler for diet main menu button.
     * 
     * @param evt 
     */
    private void dietMainMenuButtonMouseClicked(java.awt.event.MouseEvent evt) {
        cards.show(mainPanel, "mainMenuCard");
    }

    /**
     * Event handler for workout logs main menu button.
     * 
     * @param evt 
     */
    private void workoutLogMainMenuButtonMouseClicked(java.awt.event.MouseEvent evt) {
        cards.show(mainPanel, "mainMenuCard");
    }

    /**
     * Event handler for workouts main menu button.
     * 
     * @param evt 
     */
    private void workoutsMainMenuButtonMouseClicked(java.awt.event.MouseEvent evt) {
        cards.show(mainPanel, "mainMenuCard");
    }

    /**
     * Event handler for measurements main menu button.
     * 
     * @param evt 
     */
    private void measurementsMainMenuButtonMouseClicked(java.awt.event.MouseEvent evt) {
        cards.show(mainPanel, "mainMenuCard");
    }

    /**
     * Event handler for workout main menu button.
     * 
     * @param evt 
     */
    private void modifyWorkoutsMainMenuButtonMouseClicked(java.awt.event.MouseEvent evt) {
        cards.show(mainPanel, "mainMenuCard");
    }

    /**
     * Event handler for modify workout button.
     * 
     * @param evt 
     */
    private void createOrModifyWorkoutButtonMouseClicked(java.awt.event.MouseEvent evt) {
        Statement s;
        ResultSet set;
        try {
            dm.createConnection();
            connection = dm.getAdminConnection();
            s = connection.createStatement();
            //query the workout names associated with this ID
            set = s.executeQuery("SELECT workout_name " +
                                 "FROM workouts WHERE user_id = \"" +
                                  currentUserID + "\" GROUP BY workout_name;");
 
            //check to see if the user has any workouts 
            int numResults = this.getNumRecords(set);
            
            //reset iterator
            set.beforeFirst();
               
            //if no results are found, set the model to empty
            if(numResults == 0){
                addExerciseComboBox.setModel(new javax.swing.DefaultComboBoxModel());
                deleteExerciseComboBox.setModel(new javax.swing.DefaultComboBoxModel());
                exerciseNameComboBox.setModel(new javax.swing.DefaultComboBoxModel());
                exerciseTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel());
                muscleGroupComboBox.setModel(new javax.swing.DefaultComboBoxModel());
                exerciseSelectComboBox.setModel(new javax.swing.DefaultComboBoxModel());
            }
            else{
                String[] workoutNames = new String[numResults];
                int i = 0;
                
                //create a new DefaultComboBoxModel with elements from the acquired set
                addExerciseComboBox.setModel(new javax.swing.DefaultComboBoxModel());
                deleteExerciseComboBox.setModel(new javax.swing.DefaultComboBoxModel());
                
                //iterate over workout names and populate the model
                while(set.next()){
                    workoutNames[i++] = set.getString("workout_name");
                }
                addExerciseComboBox.setModel(new javax.swing.DefaultComboBoxModel(workoutNames));
                deleteExerciseComboBox.setModel(new javax.swing.DefaultComboBoxModel(workoutNames));
                
                //execute query to retrieve all exercises corresponding to the workout that is
                //currently selected
                String currentWorkoutName = deleteExerciseComboBox.getSelectedItem().toString();
                set = s.executeQuery("SELECT exercise_name " + "FROM workouts INNER JOIN exercises "
                                     + "on workouts.exercise_id = exercises.exercise_id where user_id = " +
                                     "\"" + currentUserID + "\" AND " + 
                                     "workout_name = \"" + currentWorkoutName + "\";");
                numResults = this.getNumRecords(set);
                set.beforeFirst();
                String[] exerciseNames = new String[numResults];
                int j = 0;
                //create a new DefaultComboBoxModel with elements from the acquired set
                exerciseNameComboBox.setModel(new javax.swing.DefaultComboBoxModel());
                
                //iterate over exercise names and populate the model
                while(set.next()){
                    exerciseNames[j++] = set.getString("exercise_name");
                }
                exerciseNameComboBox.setModel(new javax.swing.DefaultComboBoxModel(exerciseNames));
            }
            dm.closeConnection();
            this.setWorkoutComboBoxes(EXERCISE_TYPE_CHANGED);
        } catch (SQLException ex) {
            Logger.getLogger(WorkoutGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        cards.show(mainPanel, "modifyWorkoutsCard");
    }

    /**
     * Event handler for workout select comboBox.
     * 
     * @param evt 
     */
    private void workoutSelectDropdownMenuActionPerformed(java.awt.event.ActionEvent evt) {
        Statement s;
        ResultSet set;
        String selection = workoutSelectDropdownMenu.getSelectedItem().toString();
        workoutNameLabel.setText(selection);
        try {
            dm.createConnection();
            connection = dm.getAdminConnection();
            s = connection.createStatement();
            //query the workout names associated with this ID
            set = s.executeQuery("SELECT exercise_sets AS Sets, exercise_reps AS Reps, exercise_name "+
                                 " AS Exercise from workouts inner join exercises "
                                 + "on workouts.exercise_id = exercises.exercise_id where user_id = " +
                                "\"" + currentUserID + "\" AND " + 
                                "workout_name = \"" + selection + "\";");
 
            this.resultSetToTableModel(set, workoutDetailsTable);
            dm.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(WorkoutGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        cards.show(mainPanel, "workoutMenuCard");
    }

    /**
     * Event handler for add exercise comboBox.
     * @param evt 
     */
    private void addExerciseComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
       
    }
    
    /**
     * Event handler for the exercise type comboBox.
     * @param evt 
     */
    private void exerciseTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        this.setWorkoutComboBoxes(EXERCISE_TYPE_CHANGED);
    }

    /**
     * Event handler for the muscle group comboBox.
     * 
     * @param evt 
     */
    private void muscleGroupComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        this.setWorkoutComboBoxes(MUSCLE_GROUP_CHANGED);
    }

    /**
     * Event handler for the delete exercise comboBox.
     * 
     * @param evt 
     */
    private void deleteExerciseComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        String currentWorkoutName = deleteExerciseComboBox.getSelectedItem().toString();
        Statement s;
        ResultSet set;
        try {
            dm.createConnection();
            connection = dm.getAdminConnection();
            s = connection.createStatement();

            //query the exercise names corresponding to the current selection
            set = s.executeQuery("SELECT exercise_name " + "FROM workouts INNER JOIN exercises "
                             + "on workouts.exercise_id = exercises.exercise_id where user_id = " +
                             "\"" + currentUserID + "\" AND " + 
                             "workout_name = \"" + currentWorkoutName + "\";");
            int numResults = this.getNumRecords(set);
            set.beforeFirst();
            String[] exerciseNames = new String[numResults];
            int j = 0;
            //create a new DefaultComboBoxModel with elements from the acquired set
            exerciseNameComboBox.setModel(new javax.swing.DefaultComboBoxModel());

            //iterate over exercise names and populate the model
            while(set.next()){
                exerciseNames[j++] = set.getString("exercise_name");
            }
            exerciseNameComboBox.setModel(new javax.swing.DefaultComboBoxModel(exerciseNames));
            dm.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(WorkoutGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Event handler for the food type comboBox.
     * @param evt 
     */
    private void foodTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        setDietComboBoxes();
    }
    
    /**
     * Event handler for the drink type comboBox.
     * @param evt 
     */
    private void drinkTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        setDietComboBoxes();
    }
    
    /**
     * Event handler for the add food button.
     * @param evt 
     */
    private void addFoodButtonMouseClicked(java.awt.event.MouseEvent evt) {
        String foodName = foodNameComboBox.getSelectedItem().toString();
        String workoutName = foodWorkoutSelectComboBox.getSelectedItem().toString();
        int quantity = Integer.parseInt(foodQuantityComboBox.getSelectedItem().toString());
        this.addFood(foodName, workoutName, quantity);
    }

    /**
     * Event handler for the add drink button.
     * @param evt 
     */
    private void addDrinkButtonMouseClicked(java.awt.event.MouseEvent evt) {
        String drinkName = drinkNameComboBox.getSelectedItem().toString();
        String workoutName = drinkWorkoutSelectComboBox.getSelectedItem().toString();
        int quantity = Integer.parseInt(drinkQuantityComboBox.getSelectedItem().toString());
        this.addDrink(drinkName, workoutName, quantity);
    }

    public static void main(String args[]){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WorkoutGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WorkoutGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WorkoutGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WorkoutGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                new WorkoutGUI().setVisible(true);
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        });
    }
	
    /**
     * Initializes the components of the GUI.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        accountLoginErrorDialog = new javax.swing.JDialog();
        accountLoginErrorScrollPane = new javax.swing.JScrollPane();
        accountLoginErrorTextArea = new javax.swing.JTextArea();
        mainPanel = new javax.swing.JPanel();
        loginPanel = new javax.swing.JPanel();
        userIDTextField = new javax.swing.JTextField();
        userIDLabel = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        loginLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        dietMenuPanel = new javax.swing.JPanel();
        dietMenuLabel = new javax.swing.JLabel();
        foodTypeComboBox = new javax.swing.JComboBox<>();
        drinkTypeComboBox = new javax.swing.JComboBox<>();
        foodWorkoutSelectComboBox = new javax.swing.JComboBox<>();
        drinkWorkoutSelectComboBox = new javax.swing.JComboBox<>();
        foodNameComboBox = new javax.swing.JComboBox<>();
        drinkNameComboBox = new javax.swing.JComboBox<>();
        foodQuantityComboBox = new javax.swing.JComboBox<>();
        drinkQuantityComboBox = new javax.swing.JComboBox<>();
        foodTypeLabel = new javax.swing.JLabel();
        foodNameLabel = new javax.swing.JLabel();
        foodQuantityLabel = new javax.swing.JLabel();
        foodWorkoutSelectLabel = new javax.swing.JLabel();
        addFoodLabel = new javax.swing.JLabel();
        addFoodButton = new javax.swing.JButton();
        drinkWorkoutSelectLabel = new javax.swing.JLabel();
        drinkTypeLabel = new javax.swing.JLabel();
        drinkNameLabel = new javax.swing.JLabel();
        drinkQuantityLabel = new javax.swing.JLabel();
        addDrinkLabel = new javax.swing.JLabel();
        addDrinkButton = new javax.swing.JButton();
        dietMainMenuButton = new javax.swing.JButton();
        workoutLogPanel = new javax.swing.JPanel();
        workoutLogMenuLabel = new javax.swing.JLabel();
        workoutLogMainMenuButton = new javax.swing.JButton();
        workoutLogScrollPane = new javax.swing.JScrollPane();
        workoutLogTable = new javax.swing.JTable();
        workoutMenuPanel = new javax.swing.JPanel();
        workoutsMenuLabel = new javax.swing.JLabel();
        workoutNameLabel = new javax.swing.JLabel();
        workoutSelectDropdownMenu = new javax.swing.JComboBox<>();
        workoutSelectLabel = new javax.swing.JLabel();
        createOrModifyWorkoutButton = new javax.swing.JButton();
        workoutDetailsLabel = new javax.swing.JLabel();
        workoutsMainMenuButton = new javax.swing.JButton();
        workoutDetailsScrollPane = new javax.swing.JScrollPane();
        workoutDetailsTable = new javax.swing.JTable();
        mainMenuPanel = new javax.swing.JPanel();
        mainMenuLabel = new javax.swing.JLabel();
        workoutOptionButton = new javax.swing.JButton();
        dietOptionButton = new javax.swing.JButton();
        measurementsOptionButton = new javax.swing.JButton();
        workoutLogOptionButton = new javax.swing.JButton();
        userNameLabel = new javax.swing.JLabel();
        measurementsPanel = new javax.swing.JPanel();
        measurementsMainMenuButton = new javax.swing.JButton();
        measurementsLabel = new javax.swing.JLabel();
        measurementsScrollPane = new javax.swing.JScrollPane();
        measurementsTable = new javax.swing.JTable();
        modifyWorkoutsPanel = new javax.swing.JPanel();
        modifyWorkoutsLabel = new javax.swing.JLabel();
        addExerciseComboBox = new javax.swing.JComboBox<>();
        addExerciseComboBoxLabel = new javax.swing.JLabel();
        addExerciseLabel = new javax.swing.JLabel();
        exerciseSelectLabel = new javax.swing.JLabel();
        exerciseTypeComboBox = new javax.swing.JComboBox<>();
        muscleGroupComboBox = new javax.swing.JComboBox<>();
        addExerciseButton = new javax.swing.JButton();
        setsComboBox = new javax.swing.JComboBox<>();
        repsComboBox = new javax.swing.JComboBox<>();
        deleteExerciseComboBox = new javax.swing.JComboBox<>();
        deleteExerciseComboBoxLabel = new javax.swing.JLabel();
        deleteExerciseLabel = new javax.swing.JLabel();
        deleteExerciseSelectLabel = new javax.swing.JLabel();
        exerciseNameComboBox = new javax.swing.JComboBox<>();
        deleteExerciseButton = new javax.swing.JButton();
        exerciseTypeLabel = new javax.swing.JLabel();
        muscleGroupLabel = new javax.swing.JLabel();
        setsLabel = new javax.swing.JLabel();
        repsLabel = new javax.swing.JLabel();
        exerciseSelectComboBox = new javax.swing.JComboBox<>();
        exerciseSelectComboBoxLabel = new javax.swing.JLabel();
        modifyWorkoutsMainMenuButton = new javax.swing.JButton();

        accountLoginErrorDialog.setMinimumSize(new java.awt.Dimension(500, 500));
        accountLoginErrorDialog.setSize(new java.awt.Dimension(500, 500));

        accountLoginErrorTextArea.setEditable(false);
        accountLoginErrorTextArea.setColumns(20);
        accountLoginErrorTextArea.setFont(new java.awt.Font("Monospaced", 0, 10)); // NOI18N
        accountLoginErrorTextArea.setLineWrap(true);
        accountLoginErrorTextArea.setRows(5);
        accountLoginErrorTextArea.setText("Error: User name and/or password is/are incorrect. Please close this dialog box and try again.");
        accountLoginErrorScrollPane.setViewportView(accountLoginErrorTextArea);

        javax.swing.GroupLayout accountLoginErrorDialogLayout = new javax.swing.GroupLayout(accountLoginErrorDialog.getContentPane());
        accountLoginErrorDialog.getContentPane().setLayout(accountLoginErrorDialogLayout);
        accountLoginErrorDialogLayout.setHorizontalGroup(
            accountLoginErrorDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, accountLoginErrorDialogLayout.createSequentialGroup()
                .addContainerGap(123, Short.MAX_VALUE)
                .addComponent(accountLoginErrorScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
        );
        accountLoginErrorDialogLayout.setVerticalGroup(
            accountLoginErrorDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountLoginErrorDialogLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(accountLoginErrorScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addGap(51, 51, 51))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(1000, 550));

        mainPanel.setMaximumSize(new java.awt.Dimension(1000, 550));
        mainPanel.setPreferredSize(new java.awt.Dimension(1000, 550));
        mainPanel.setLayout(new java.awt.CardLayout());

        userIDLabel.setText("User ID:");

        loginButton.setText("Login");
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginButtonMouseClicked(evt);
            }
        });

        loginLabel.setText("Login To Your Account");

        passwordLabel.setText("Password:");

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(303, 303, 303)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(passwordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(passwordField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(userIDTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addComponent(loginLabel)
                        .addGap(111, 111, 111)))
                .addContainerGap(350, Short.MAX_VALUE))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(loginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userIDLabel)
                    .addComponent(userIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginButton)
                .addContainerGap(258, Short.MAX_VALUE))
        );

        mainPanel.add(loginPanel, "loginCard");

        dietMenuPanel.setMaximumSize(new java.awt.Dimension(1000, 550));
        dietMenuPanel.setPreferredSize(new java.awt.Dimension(1000, 550));

        dietMenuLabel.setText("Diet");

        foodTypeComboBox.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        foodTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        foodTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foodTypeComboBoxActionPerformed(evt);
            }
        });

        drinkTypeComboBox.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        drinkTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        drinkTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drinkTypeComboBoxActionPerformed(evt);
            }
        });

        foodWorkoutSelectComboBox.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        foodWorkoutSelectComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        drinkWorkoutSelectComboBox.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        drinkWorkoutSelectComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        foodNameComboBox.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        foodNameComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        drinkNameComboBox.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        drinkNameComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        foodQuantityComboBox.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        foodQuantityComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        drinkQuantityComboBox.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        drinkQuantityComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        foodTypeLabel.setText("Food Type");

        foodNameLabel.setText("Food Name");

        foodQuantityLabel.setText("Quantity");

        foodWorkoutSelectLabel.setText("Workout");

        addFoodLabel.setText("Add Food");

        addFoodButton.setText("Add Food");
        addFoodButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addFoodButtonMouseClicked(evt);
            }
        });

        drinkWorkoutSelectLabel.setText("Workout");

        drinkTypeLabel.setText("Drink Type");

        drinkNameLabel.setText("Drink Name");

        drinkQuantityLabel.setText("Quantity");

        addDrinkLabel.setText("Add Drink");

        addDrinkButton.setText("Add Drink");
        addDrinkButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addDrinkButtonMouseClicked(evt);
            }
        });

        dietMainMenuButton.setText("Main Menu");
        dietMainMenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dietMainMenuButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout dietMenuPanelLayout = new javax.swing.GroupLayout(dietMenuPanel);
        dietMenuPanel.setLayout(dietMenuPanelLayout);
        dietMenuPanelLayout.setHorizontalGroup(
            dietMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dietMenuPanelLayout.createSequentialGroup()
                .addGroup(dietMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dietMenuPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dietMainMenuButton))
                    .addGroup(dietMenuPanelLayout.createSequentialGroup()
                        .addGap(438, 438, 438)
                        .addComponent(dietMenuLabel))
                    .addGroup(dietMenuPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(addFoodLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(dietMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(foodWorkoutSelectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(foodWorkoutSelectComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dietMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(foodTypeComboBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(dietMenuPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(foodTypeLabel)
                                .addGap(18, 18, 18)))
                        .addGroup(dietMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(dietMenuPanelLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(foodNameLabel)
                                .addGap(47, 47, 47)
                                .addComponent(foodQuantityLabel))
                            .addGroup(dietMenuPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(dietMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(addFoodButton)
                                    .addGroup(dietMenuPanelLayout.createSequentialGroup()
                                        .addComponent(foodNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(foodQuantityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(58, 58, 58)
                        .addComponent(addDrinkLabel)
                        .addGap(18, 18, 18)
                        .addGroup(dietMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(drinkWorkoutSelectComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(drinkWorkoutSelectLabel))
                        .addGap(18, 18, 18)
                        .addGroup(dietMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(drinkTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(drinkTypeLabel))
                        .addGap(18, 18, 18)
                        .addGroup(dietMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(dietMenuPanelLayout.createSequentialGroup()
                                .addGroup(dietMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(dietMenuPanelLayout.createSequentialGroup()
                                        .addComponent(drinkNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(dietMenuPanelLayout.createSequentialGroup()
                                        .addComponent(drinkNameLabel)
                                        .addGap(41, 41, 41)))
                                .addGroup(dietMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(drinkQuantityLabel)
                                    .addComponent(drinkQuantityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(dietMenuPanelLayout.createSequentialGroup()
                                .addComponent(addDrinkButton)
                                .addGap(1, 1, 1)))))
                .addContainerGap(188, Short.MAX_VALUE))
        );
        dietMenuPanelLayout.setVerticalGroup(
            dietMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dietMenuPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(dietMenuLabel)
                .addGap(65, 65, 65)
                .addGroup(dietMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dietMenuPanelLayout.createSequentialGroup()
                        .addGroup(dietMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(drinkQuantityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(drinkNameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dietMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(drinkNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(drinkQuantityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(dietMenuPanelLayout.createSequentialGroup()
                        .addGroup(dietMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(foodTypeLabel)
                            .addComponent(foodWorkoutSelectLabel)
                            .addComponent(foodNameLabel)
                            .addComponent(foodQuantityLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dietMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(foodTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(foodWorkoutSelectComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addFoodLabel)
                            .addComponent(foodNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(foodQuantityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addComponent(addFoodButton))
                    .addGroup(dietMenuPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(addDrinkLabel))
                    .addGroup(dietMenuPanelLayout.createSequentialGroup()
                        .addGroup(dietMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(drinkTypeLabel)
                            .addComponent(drinkWorkoutSelectLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(dietMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(drinkTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(drinkWorkoutSelectComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(dietMenuPanelLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(addDrinkButton)))
                .addGap(144, 144, 144)
                .addComponent(dietMainMenuButton)
                .addGap(189, 189, 189))
        );

        mainPanel.add(dietMenuPanel, "dietMenuCard");

        workoutLogPanel.setMaximumSize(new java.awt.Dimension(1000, 550));
        workoutLogPanel.setPreferredSize(new java.awt.Dimension(1000, 550));

        workoutLogMenuLabel.setText("Workout Log");

        workoutLogMainMenuButton.setText("Main Menu");
        workoutLogMainMenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                workoutLogMainMenuButtonMouseClicked(evt);
            }
        });

        workoutLogTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        workoutLogScrollPane.setViewportView(workoutLogTable);

        javax.swing.GroupLayout workoutLogPanelLayout = new javax.swing.GroupLayout(workoutLogPanel);
        workoutLogPanel.setLayout(workoutLogPanelLayout);
        workoutLogPanelLayout.setHorizontalGroup(
            workoutLogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(workoutLogPanelLayout.createSequentialGroup()
                .addGroup(workoutLogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(workoutLogPanelLayout.createSequentialGroup()
                        .addGap(452, 452, 452)
                        .addComponent(workoutLogMenuLabel))
                    .addGroup(workoutLogPanelLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(workoutLogMainMenuButton))
                    .addGroup(workoutLogPanelLayout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(workoutLogScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(281, Short.MAX_VALUE))
        );
        workoutLogPanelLayout.setVerticalGroup(
            workoutLogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, workoutLogPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(workoutLogMenuLabel)
                .addGap(18, 18, 18)
                .addComponent(workoutLogScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(workoutLogMainMenuButton)
                .addGap(69, 69, 69))
        );

        mainPanel.add(workoutLogPanel, "workoutLogCard");

        workoutMenuPanel.setMaximumSize(new java.awt.Dimension(1000, 550));
        workoutMenuPanel.setPreferredSize(new java.awt.Dimension(1000, 550));

        workoutsMenuLabel.setText("Workouts");

        workoutSelectDropdownMenu.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        workoutSelectDropdownMenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        workoutSelectDropdownMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workoutSelectDropdownMenuActionPerformed(evt);
            }
        });

        workoutSelectLabel.setText("Select Workout:");

        createOrModifyWorkoutButton.setText("Modify Workout");
        createOrModifyWorkoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createOrModifyWorkoutButtonMouseClicked(evt);
            }
        });

        workoutDetailsLabel.setText("View Workout Details");

        workoutsMainMenuButton.setText("Main Menu");
        workoutsMainMenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                workoutsMainMenuButtonMouseClicked(evt);
            }
        });

        workoutDetailsTable.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        workoutDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        workoutDetailsScrollPane.setViewportView(workoutDetailsTable);

        javax.swing.GroupLayout workoutMenuPanelLayout = new javax.swing.GroupLayout(workoutMenuPanel);
        workoutMenuPanel.setLayout(workoutMenuPanelLayout);
        workoutMenuPanelLayout.setHorizontalGroup(
            workoutMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(workoutMenuPanelLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(createOrModifyWorkoutButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, workoutMenuPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(workoutsMenuLabel)
                .addGap(461, 461, 461))
            .addGroup(workoutMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, workoutMenuPanelLayout.createSequentialGroup()
                    .addGap(506, 506, 506)
                    .addComponent(workoutNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(413, 413, 413))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, workoutMenuPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(workoutsMainMenuButton)
                    .addGap(56, 56, 56)
                    .addGroup(workoutMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(workoutMenuPanelLayout.createSequentialGroup()
                            .addComponent(workoutDetailsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(42, 42, 42))
                        .addGroup(workoutMenuPanelLayout.createSequentialGroup()
                            .addComponent(workoutSelectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(workoutSelectDropdownMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                    .addComponent(workoutDetailsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        workoutMenuPanelLayout.setVerticalGroup(
            workoutMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(workoutMenuPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(workoutsMenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(createOrModifyWorkoutButton)
                .addGap(60, 60, 60)
                .addComponent(workoutNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(workoutMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(workoutsMainMenuButton)
                    .addGroup(workoutMenuPanelLayout.createSequentialGroup()
                        .addComponent(workoutDetailsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(workoutMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(workoutSelectLabel)
                            .addComponent(workoutSelectDropdownMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(workoutDetailsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(123, Short.MAX_VALUE))
        );

        mainPanel.add(workoutMenuPanel, "workoutMenuCard");

        mainMenuPanel.setMaximumSize(new java.awt.Dimension(1000, 550));
        mainMenuPanel.setPreferredSize(new java.awt.Dimension(1000, 550));

        mainMenuLabel.setText("Main Menu");

        workoutOptionButton.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        workoutOptionButton.setText("Workouts");
        workoutOptionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                workoutOptionButtonMouseClicked(evt);
            }
        });

        dietOptionButton.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        dietOptionButton.setText("Diet");
        dietOptionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dietOptionButtonMouseClicked(evt);
            }
        });

        measurementsOptionButton.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        measurementsOptionButton.setText("Measurements");
        measurementsOptionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                measurementsOptionButtonMouseClicked(evt);
            }
        });

        workoutLogOptionButton.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        workoutLogOptionButton.setText("Workout Log");
        workoutLogOptionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                workoutLogOptionButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout mainMenuPanelLayout = new javax.swing.GroupLayout(mainMenuPanel);
        mainMenuPanel.setLayout(mainMenuPanelLayout);
        mainMenuPanelLayout.setHorizontalGroup(
            mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainMenuPanelLayout.createSequentialGroup()
                .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainMenuPanelLayout.createSequentialGroup()
                        .addGap(460, 460, 460)
                        .addComponent(mainMenuLabel))
                    .addGroup(mainMenuPanelLayout.createSequentialGroup()
                        .addGap(433, 433, 433)
                        .addGroup(mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(workoutLogOptionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dietOptionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(workoutOptionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(measurementsOptionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(mainMenuPanelLayout.createSequentialGroup()
                        .addGap(415, 415, 415)
                        .addComponent(userNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(451, Short.MAX_VALUE))
        );
        mainMenuPanelLayout.setVerticalGroup(
            mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainMenuPanelLayout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(mainMenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(userNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(workoutOptionButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dietOptionButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(measurementsOptionButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(workoutLogOptionButton)
                .addContainerGap(259, Short.MAX_VALUE))
        );

        mainPanel.add(mainMenuPanel, "mainMenuCard");

        measurementsPanel.setMaximumSize(new java.awt.Dimension(1000, 550));
        measurementsPanel.setPreferredSize(new java.awt.Dimension(1000, 550));

        measurementsMainMenuButton.setText("Main Menu");
        measurementsMainMenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                measurementsMainMenuButtonMouseClicked(evt);
            }
        });

        measurementsLabel.setText("Measurements");

        measurementsTable.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        measurementsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        measurementsScrollPane.setViewportView(measurementsTable);

        javax.swing.GroupLayout measurementsPanelLayout = new javax.swing.GroupLayout(measurementsPanel);
        measurementsPanel.setLayout(measurementsPanelLayout);
        measurementsPanelLayout.setHorizontalGroup(
            measurementsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(measurementsPanelLayout.createSequentialGroup()
                .addGroup(measurementsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(measurementsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(measurementsMainMenuButton))
                    .addGroup(measurementsPanelLayout.createSequentialGroup()
                        .addGap(382, 382, 382)
                        .addComponent(measurementsLabel))
                    .addGroup(measurementsPanelLayout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(measurementsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(357, Short.MAX_VALUE))
        );
        measurementsPanelLayout.setVerticalGroup(
            measurementsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, measurementsPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(measurementsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(measurementsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(measurementsMainMenuButton)
                .addGap(68, 68, 68))
        );

        mainPanel.add(measurementsPanel, "measurementsCard");

        modifyWorkoutsLabel.setText("Modify Workouts");

        addExerciseComboBox.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        addExerciseComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        addExerciseComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addExerciseComboBoxActionPerformed(evt);
            }
        });

        addExerciseComboBoxLabel.setText("Select Workout:");

        addExerciseLabel.setText("Add Exercise to Existing Workout");

        exerciseSelectLabel.setText("Select Exercise:");

        exerciseTypeComboBox.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        exerciseTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        exerciseTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exerciseTypeComboBoxActionPerformed(evt);
            }
        });

        muscleGroupComboBox.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        muscleGroupComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        muscleGroupComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muscleGroupComboBoxActionPerformed(evt);
            }
        });

        addExerciseButton.setText("Add Exercise");
        addExerciseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addExerciseButtonMouseClicked(evt);
            }
        });

        setsComboBox.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        setsComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        repsComboBox.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        repsComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        deleteExerciseComboBox.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        deleteExerciseComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        deleteExerciseComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteExerciseComboBoxActionPerformed(evt);
            }
        });

        deleteExerciseComboBoxLabel.setText("Select Workout:");

        deleteExerciseLabel.setText("Delete Exercise From Existing Workout");

        deleteExerciseSelectLabel.setText("Select Exercise:");

        exerciseNameComboBox.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        exerciseNameComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        deleteExerciseButton.setText("Delete Exercise");
        deleteExerciseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteExerciseButtonMouseClicked(evt);
            }
        });

        exerciseTypeLabel.setText("Exercise Type");

        muscleGroupLabel.setText("Muscle Group");

        setsLabel.setText("Sets");

        repsLabel.setText("Reps");

        exerciseSelectComboBox.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        exerciseSelectComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        exerciseSelectComboBoxLabel.setText("Exercise");

        modifyWorkoutsMainMenuButton.setText("Main Menu");
        modifyWorkoutsMainMenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modifyWorkoutsMainMenuButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout modifyWorkoutsPanelLayout = new javax.swing.GroupLayout(modifyWorkoutsPanel);
        modifyWorkoutsPanel.setLayout(modifyWorkoutsPanelLayout);
        modifyWorkoutsPanelLayout.setHorizontalGroup(
            modifyWorkoutsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modifyWorkoutsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(modifyWorkoutsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(394, 394, 394))
            .addGroup(modifyWorkoutsPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(modifyWorkoutsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modifyWorkoutsPanelLayout.createSequentialGroup()
                        .addGap(281, 281, 281)
                        .addGroup(modifyWorkoutsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modifyWorkoutsPanelLayout.createSequentialGroup()
                                .addGroup(modifyWorkoutsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(deleteExerciseComboBoxLabel)
                                    .addComponent(deleteExerciseSelectLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(modifyWorkoutsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(deleteExerciseComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(exerciseNameComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(62, 62, 62)
                                .addComponent(deleteExerciseButton))
                            .addGroup(modifyWorkoutsPanelLayout.createSequentialGroup()
                                .addGroup(modifyWorkoutsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(deleteExerciseLabel)
                                    .addGroup(modifyWorkoutsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(addExerciseLabel)
                                        .addGroup(modifyWorkoutsPanelLayout.createSequentialGroup()
                                            .addComponent(addExerciseComboBoxLabel)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(addExerciseComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(modifyWorkoutsPanelLayout.createSequentialGroup()
                                .addGroup(modifyWorkoutsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(modifyWorkoutsPanelLayout.createSequentialGroup()
                                        .addComponent(exerciseSelectLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(exerciseTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modifyWorkoutsPanelLayout.createSequentialGroup()
                                        .addComponent(exerciseTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(modifyWorkoutsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(modifyWorkoutsPanelLayout.createSequentialGroup()
                                        .addComponent(muscleGroupLabel)
                                        .addGap(35, 35, 35)
                                        .addComponent(exerciseSelectComboBoxLabel))
                                    .addGroup(modifyWorkoutsPanelLayout.createSequentialGroup()
                                        .addComponent(muscleGroupComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(exerciseSelectComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(9, 9, 9)
                                .addGroup(modifyWorkoutsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(setsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(modifyWorkoutsPanelLayout.createSequentialGroup()
                                        .addComponent(setsLabel)
                                        .addGap(17, 17, 17)))
                                .addGap(18, 18, 18)
                                .addGroup(modifyWorkoutsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(repsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(modifyWorkoutsPanelLayout.createSequentialGroup()
                                        .addComponent(repsLabel)
                                        .addGap(20, 20, 20))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modifyWorkoutsPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(addExerciseButton))))
                    .addComponent(modifyWorkoutsMainMenuButton))
                .addGap(285, 285, 285))
        );
        modifyWorkoutsPanelLayout.setVerticalGroup(
            modifyWorkoutsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modifyWorkoutsPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(modifyWorkoutsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(addExerciseLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(modifyWorkoutsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addExerciseComboBoxLabel)
                    .addComponent(addExerciseComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(modifyWorkoutsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modifyWorkoutsPanelLayout.createSequentialGroup()
                        .addGroup(modifyWorkoutsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modifyWorkoutsPanelLayout.createSequentialGroup()
                                .addGroup(modifyWorkoutsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(setsLabel)
                                    .addComponent(repsLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(modifyWorkoutsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(setsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(repsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(exerciseSelectComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(addExerciseButton))
                    .addGroup(modifyWorkoutsPanelLayout.createSequentialGroup()
                        .addGroup(modifyWorkoutsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modifyWorkoutsPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(modifyWorkoutsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(exerciseSelectLabel)
                                    .addComponent(exerciseTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(modifyWorkoutsPanelLayout.createSequentialGroup()
                                .addGroup(modifyWorkoutsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(muscleGroupLabel)
                                    .addComponent(exerciseSelectComboBoxLabel)
                                    .addComponent(exerciseTypeLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(muscleGroupComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(60, 60, 60)
                        .addComponent(deleteExerciseLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(modifyWorkoutsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deleteExerciseComboBoxLabel)
                            .addComponent(deleteExerciseComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(modifyWorkoutsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deleteExerciseSelectLabel)
                            .addComponent(exerciseNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteExerciseButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(modifyWorkoutsMainMenuButton)
                .addGap(142, 142, 142))
        );

        mainPanel.add(modifyWorkoutsPanel, "modifyWorkoutsCard");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }
}
