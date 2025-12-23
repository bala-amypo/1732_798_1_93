@Repository
public interface InteractionRuleRepository extends JpaRepository<InteractionRule, Long> {
    
    // Find rule between two ingredients (bidirectional)
    @Query("SELECT r FROM InteractionRule r WHERE " +
           "(r.ingredientA.id = :id1 AND r.ingredientB.id = :id2) OR " +
           "(r.ingredientA.id = :id2 AND r.ingredientB.id = :id1)")
    Optional<InteractionRule> findRuleBetweenIngredients(@Param("id1") Long id1, @Param("id2") Long id2);
    
    // Find rules by medication ID
    @Query("SELECT r FROM InteractionRule r WHERE " +
           "r.medication1Id = :medicationId OR r.medication2Id = :medicationId")
    List<InteractionRule> findByMedicationId(@Param("medicationId") Long medicationId);
    
    // Find rules by ingredient ID
    @Query("SELECT r FROM InteractionRule r WHERE " +
           "r.ingredientA.id = :ingredientId OR r.ingredientB.id = :ingredientId")
    List<InteractionRule> findByIngredientId(@Param("ingredientId") Long ingredientId);
    
    // Find rules by severity
    List<InteractionRule> findBySeverityIgnoreCase(String severity);
}